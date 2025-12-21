package Beans;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class PointBean implements Serializable {

    private BigDecimal x;
    private BigDecimal y;
    private BigDecimal r;
    private Double rInput;
    private Integer rIndex;


    private boolean fromGraph = false;
    private BigDecimal graphX;
    private BigDecimal graphY;

    private boolean lastHitResult;

    @Inject
    DataBaseService service;

    public String clear() {
        this.x = null;
        this.y = null;
        this.r = null;
        this.graphX = null;
        this.graphY = null;
        this.fromGraph = false;
        return null;
    }

    public String clearResults() {
        service.clearResults();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Успех", "Все результаты очищены"));
        return null;
    }

    public String syncHistoryToYaDisk() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        try {
            service.syncHistoryToYaDisk();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Успех", "Синхронизация завершена"));
        } catch (RuntimeException e) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка", e.getMessage()));
        }
        return null;
    }


    public String checkHit() {
        FacesContext context = FacesContext.getCurrentInstance();

        boolean hasErrors = false;


        // если точка пришла с графика — подменяем x/y (r берётся из выбранного значения)
        if (fromGraph) {
            this.x = graphX;
            this.y = graphY;
        }

        if (r == null) {
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка", "Выберите радиус R"));
            fromGraph = false;
            return null;
        }

        if (x == null) {
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка", "Выберите координату X"));
            fromGraph = false;
            return null;
        }

        if (y == null) {
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка", "Введите координату Y"));
            hasErrors = true;
        } else {
            if (!fromGraph) {
                if (y.compareTo(new BigDecimal("-5")) < 0 || y.compareTo(new BigDecimal("5")) > 0) {
                    context.addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка", "Y должен быть в диапазоне от -5 до 5"));
                    hasErrors = true;
                }
            }
        }


        if (hasErrors) {
            fromGraph = false;
            return null;
        }

        Point point = new Point(x, y, r);
        boolean isHit = AreaCheckerBean.isHit(point);
        this.lastHitResult = isHit;

        service.saveResult(x, y, r, isHit);

        context.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Результат", isHit ? "Попадание!" : "Промах!"));

        if (fromGraph) {
            this.graphX = null;
            this.graphY = null;
        }
        fromGraph = false;

        return null;
    }

    public List<ResultPoint> getResultsForCurrentRadius() {
        if (r == null) {
            return new ArrayList<>();
        }
        return service.getResultsByRadius(r);
    }

    public List<ResultPoint> getResults() {
        return service.getAllResults();
    }

    public int getResultsCount() {
        return service.getResultsCount();
    }

    public String checkHitFromGraph() {
        fromGraph = true;
        return checkHit();
    }

    public Boolean getLastHitResult() {
        return lastHitResult;
    }

    public void setLastHitResult(Boolean lastHitResult) {
        this.lastHitResult = (lastHitResult != null && lastHitResult);
    }

    public BigDecimal getX() {
        return x;
    }

    public void setX(BigDecimal x) {
        this.x = x;
    }

    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }

    public BigDecimal getR() {
        return r;
    }

    public void setR(BigDecimal r) {
        this.r = r;
    }

    public BigDecimal getGraphX() {
        return graphX;
    }

    public void setGraphX(BigDecimal graphX) {
        this.graphX = graphX;
    }

    public BigDecimal getGraphY() {
        return graphY;
    }

    public void setGraphY(BigDecimal graphY) {
        this.graphY = graphY;
    }

    public boolean isFromGraph() {
        return fromGraph;
    }

    public void setFromGraph(boolean fromGraph) {
        this.fromGraph = fromGraph;
    }

    public Double getrInput() {
        return rInput;
    }

    public void setrInput(Double rInput) {
        this.rInput = rInput;
        if (rInput != null) {
            this.r = BigDecimal.valueOf(rInput);
        } else {
            this.r = null;
        }
    }

    public Integer getrIndex() {
        return rIndex;
    }

    public void setrIndex(Integer rIndex) {
        this.rIndex = rIndex;
        if (rIndex == null) {
            this.r = null;
        } else {
            this.r = new BigDecimal("2").add(new BigDecimal("0.25").multiply(new BigDecimal(rIndex)));
        }
    }
}
