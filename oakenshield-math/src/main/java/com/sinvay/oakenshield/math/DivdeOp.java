package com.sinvay.oakenshield.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 数学运算
 *
 * @author pizzalord
 * @since 1.0
 */
public class DivdeOp {
    /**
     * 默认除法运算精度
     */
    private static final int DEFAUT_DIV_SCALE = 10;

    public static BigDecimal divide(Number v1, Number v2) {
        return divide(v1, v2, DEFAUT_DIV_SCALE);
    }

    public static BigDecimal divide(Number v1, Number v2, int scale) {
        return divide(v1, v2, scale, RoundingMode.HALF_UP);
    }

    public static BigDecimal divide(Number v1, Number v2, int scale, RoundingMode roundingMode) {
        return divide(v1.toString(), v2.toString(), scale, roundingMode);
    }

    public static BigDecimal divide(String v1, String v2, int scale, RoundingMode roundingMode) {
        return divide(new BigDecimal(v1), new BigDecimal(v2), scale, roundingMode);
    }

    public static BigDecimal divide(BigDecimal v1, BigDecimal v2, int scale, RoundingMode roundingMode) {
        if (v2 == null) {
            throw new RuntimeException("Divisor must be not null !");
        }
        if (null == v1) {
            return BigDecimal.ZERO;
        }
        if (scale < 0) {
            scale = -scale;
        }
        return v1.divide(v2, scale, roundingMode);
    }
}
