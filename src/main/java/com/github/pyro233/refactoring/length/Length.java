package com.github.pyro233.refactoring.length;

/**
 * @Author: tao.zhou
 * @Date: 2022/10/16 21:55
 */
public class Length {
    private final double value;
    private final Unit unit;

    public Length(final double value, final Unit unit) {
        this.value = value;
        this.unit = unit;
    }

    public Length as(final Unit unit) {
        return new Length(newValueIn(unit), unit);
    }

    private double newValueIn(final Unit unit) {
        if (this.unit == Unit.FOOT) {
            if (unit == Unit.YARD) {
                return this.value / 3;
            } else if (unit == Unit.INCH) {
                return this.value * 12;
            }
        }

        if (this.unit == Unit.YARD) {
            if (unit == Unit.INCH) {
                return this.value * 36;
            } else if (unit == Unit.FOOT) {
                return this.value * 3;
            }
        }

        if (this.unit == Unit.INCH) {
            if (unit == Unit.FOOT) {
                return this.value / 12;
            } else if (unit == Unit.YARD) {
                return this.value / 36;
            }
        }
        return this.value;
    }

    public double getValue() {
        return this.value;
    }

    public Unit getUnit() {
        return this.unit;
    }
}
