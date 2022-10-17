package com.github.pyro233.refactoring.length;

/**
 * @Author: tao.zhou
 * @Date: 2022/10/16 21:55
 */
public class Length {
    private final double value;
    private final Unit temp_unit;

    public Length(final double value, final Unit temp_unit) {
        this.value = value;
        this.temp_unit = temp_unit;
    }

    public Length as(final Unit unit) {
        Length len = this;
        if (temp_unit == Unit.FOOT) {
            if (unit == Unit.YARD) {
                len = new Length(this.value / 3, unit);
            } else if (unit == Unit.INCH) {
                len = new Length(this.value * 12, unit);
            }
        }

        if (temp_unit == Unit.YARD) {
            if (unit == Unit.INCH) {
                len = new Length(this.value * 36, unit);
            } else if (unit == Unit.FOOT) {
                len = new Length(this.value * 3, unit);
            }
        }

        if (temp_unit == Unit.INCH) {
            if (unit == Unit.FOOT) {
                len = new Length(this.value / 12, unit);
            } else if (unit == Unit.YARD) {
                len = new Length(this.value / 36, unit);
            }
        }

        return len;
    }

    public double getValue() {
        return this.value;
    }

    public Unit getTempUnit() {
        return this.temp_unit;
    }
}
