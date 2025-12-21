package Beans;

import java.math.BigDecimal;
import java.util.Date;

public class ResultPoint {

    private final BigDecimal x;
    private final BigDecimal y;
    private final BigDecimal r;
    private final Boolean hit;
    private final Date timestamp;

    public ResultPoint(BigDecimal x, BigDecimal y, BigDecimal r, Boolean hit, Date timestamp) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.hit = hit;
        this.timestamp = timestamp;
    }

    public BigDecimal getX() {
        return x;
    }

    public BigDecimal getY() {
        return y;
    }

    public BigDecimal getR() {
        return r;
    }

    public Boolean getHit() {
        return hit;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
