package git.olegmusic.validation;

import java.math.BigDecimal;

public class Validate {
    private String err = "ok";

    public boolean check(String xs, String ys, String rs) {
        try {
            int x = Integer.parseInt(xs);
            if (x < -5 || x > 3) {
                err = "X must be in [-5..3]";
                return false;
            }
        } catch (Exception e) {
            err = "X must be integer";
            return false;
        }

        BigDecimal y;
        try {
            y = new BigDecimal(ys.replace(',', '.'));
        } catch (Exception e) {
            err = "Y must be a number";
            return false;
        }

        if (y.compareTo(BigDecimal.valueOf(-3)) < 0 || y.compareTo(BigDecimal.valueOf(5)) > 0) {
            err = "Y must be in [-3,5]";
            return false;
        }

        BigDecimal r;
        try {
            r = new BigDecimal(rs.replace(',', '.'));
        } catch (Exception e) {
            err = "R must be a number";
            return false;
        }

        BigDecimal[] validR = {
                BigDecimal.valueOf(1),
                new BigDecimal("1.5"),
                BigDecimal.valueOf(2),
                new BigDecimal("2.5"),
                BigDecimal.valueOf(3)
        };

        boolean match = false;
        for (BigDecimal vr : validR) {
            if (r.compareTo(vr) == 0) {
                match = true;
                break;
            }
        }

        if (!match) {
            err = "R must be one of [1,1.5,2,2.5,3]";
            return false;
        }

        return true;
    }

    public String getErr() {
        return err;
    }
}
