package Beans;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import java.math.BigDecimal;

@Named
@ApplicationScoped
public class AreaCheckerBean {

    private static final BigDecimal ZERO = BigDecimal.ZERO;
    private static final BigDecimal TWO = new BigDecimal("2");

    public static boolean isHit(Point point) {
        BigDecimal x = point.getX();
        BigDecimal y = point.getY();
        BigDecimal r = point.getR();

        // Отрицательный радиус — отражаем область относительно (0,0)
        if (r.compareTo(ZERO) < 0) {
            x = x.negate();
            y = y.negate();
            r = r.negate();
        }

        // 1) Четверть круга во 2 квадранте: x<=0, y>=0, x^2 + y^2 <= r^2
        if (x.compareTo(ZERO) <= 0 &&
                y.compareTo(ZERO) >= 0 &&
                x.multiply(x).add(y.multiply(y)).compareTo(r.multiply(r)) <= 0) {
            return true;
        }

        // 2) Прямоугольник в 3 квадранте: x ∈ [-r;0], y ∈ [-r/2;0]
        BigDecimal halfR = r.divide(TWO);

        if (x.compareTo(ZERO) <= 0 &&
                y.compareTo(ZERO) <= 0 &&
                x.compareTo(r.negate()) >= 0 &&
                y.compareTo(halfR.negate()) >= 0) {
            return true;
        }


        // 3) Треугольник в 4 квадранте:
        // вершины (0,0), (r/2,0), (0,-r/2)
        // гипотенуза: y = x - r/2
        if (x.compareTo(ZERO) >= 0 &&
                y.compareTo(ZERO) <= 0 &&
                y.compareTo(x.subtract(r.divide(TWO))) >= 0) {
            return true;
        }

        return false;
    }
}
