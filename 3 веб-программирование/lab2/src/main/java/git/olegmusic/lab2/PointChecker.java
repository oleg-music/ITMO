package git.olegmusic.lab2;

import java.math.BigDecimal;

public class PointChecker {

    private static final BigDecimal ZERO = BigDecimal.ZERO;
    private static final BigDecimal FOUR = BigDecimal.valueOf(4);

    public static boolean check(BigDecimal x, BigDecimal y, BigDecimal r) {
        if (x == null || y == null || r == null || r.compareTo(ZERO) <= 0) {
            return false;
        }

        BigDecimal minusR = r.negate();
        BigDecimal halfR = r.divide(BigDecimal.valueOf(2), 20, BigDecimal.ROUND_HALF_UP);

        boolean inTriangle =
                x.compareTo(ZERO) <= 0 && x.compareTo(minusR) >= 0 &&
                        y.compareTo(ZERO) >= 0 && y.compareTo(x.add(r)) <= 0;

        boolean inRectangle =
                x.compareTo(ZERO) >= 0 && x.compareTo(halfR) <= 0 &&
                        y.compareTo(ZERO) <= 0 && y.compareTo(minusR) >= 0;

        boolean inCircle = false;
        if (x.compareTo(ZERO) >= 0 && y.compareTo(ZERO) >= 0) {
            BigDecimal left = x.pow(2).add(y.pow(2)).multiply(FOUR);
            BigDecimal right = r.pow(2);
            inCircle = left.compareTo(right) <= 0;
        }

        return inTriangle || inRectangle || inCircle;
    }
}
