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

    public Length(double value, String unit) {
        this(value, unit, temp_determineUnit(unit));
    }

    public Length(final double value, final String unit, final Unit temp_unit) {
        this.value = value;
        this.unit = unit;
        this.temp_unit = temp_unit;
    }

    public static Unit temp_determineUnit(String targetUnit) {
        Unit temp_unit = null;
        if (targetUnit.equals(Length.INCH)) {
            temp_unit = Unit.INCH;
        }
        if (targetUnit.equals(Length.YARD)) {
            temp_unit = Unit.YARD;
        }
        if (targetUnit.equals(Length.FOOT)) {
            temp_unit = Unit.FOOT;
        }
        return temp_unit;
    }

    public Length as(final String u, final Unit unit) {
        Length len = this;
        if (this.unit.equals(FOOT)) {
            if (unit == Unit.YARD) {
                len = new Length(this.value / 3, u);
            } else if (unit == Unit.INCH) {
                len = new Length(this.value * 12, u);
            }
        }

        if (this.unit.equals(YARD)) {
            if (unit == Unit.INCH) {
                len = new Length(this.value * 36, u);
            } else if (unit == Unit.FOOT) {
                len = new Length(this.value * 3, u);
            }
        }

        if (this.unit.equals(INCH)) {
            if (unit == Unit.FOOT) {
                len = new Length(this.value / 12, u);
            } else if (unit == Unit.YARD) {
                len = new Length(this.value / 36, u);
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
