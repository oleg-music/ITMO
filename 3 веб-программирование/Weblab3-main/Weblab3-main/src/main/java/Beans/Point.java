package Beans;

import java.math.BigDecimal;

public class Point {

    private final BigDecimal x;
    private final BigDecimal y;
    private final BigDecimal r;

    public Point(BigDecimal x, BigDecimal y, BigDecimal r) {
        this.x = x;
        this.y = y;
        this.r = r;
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
}
