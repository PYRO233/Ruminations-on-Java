package com.github.pyro233.refactoring.length;

/**
 * @Author: tao.zhou
 * @Date: 2022/10/16 21:55
 */
public class Length {
    public static final String YARD = "yard";
    public static final String INCH = "inch";
    public static final String FOOT = "foot";
    private final double value;
    private final String unit;
    private final Unit temp_unit;

    public Length(final double value, final String unit, final Unit temp_unit) {
        this.value = value;
        this.unit = unit;
        this.temp_unit = temp_unit;
    }

    public Length as(final String u, final Unit unit) {
        Length len = this;
        if (this.unit.equals(FOOT)) {
            if (unit == Unit.YARD) {
                len = new Length(this.value / 3, u, unit);
            } else if (unit == Unit.INCH) {
                len = new Length(this.value * 12, u, unit);
            }
        }

        if (this.unit.equals(YARD)) {
            if (unit == Unit.INCH) {
                len = new Length(this.value * 36, u, unit);
            } else if (unit == Unit.FOOT) {
                len = new Length(this.value * 3, u, unit);
            }
        }

        if (this.unit.equals(INCH)) {
            if (unit == Unit.FOOT) {
                len = new Length(this.value / 12, u, unit);
            } else if (unit == Unit.YARD) {
                len = new Length(this.value / 36, u, unit);
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
