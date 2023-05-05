package com.github.pyro233.ood.ricksGuitars;

/**
 * @Author: tao.zhou
 * @Date: 2023/5/5 14:10
 */
public class MandolinSpec extends InstrumentSpec {

    private Style style;

    public MandolinSpec(Builder builder, String model, Type type,
                        Style style, Wood backWood, Wood topWood) {
        super(builder, model, type, backWood, topWood);
        this.style = style;
    }

    public Style getStyle() {
        return style;
    }

    @Override
    public boolean matches(InstrumentSpec otherSpec) {
        if (!super.matches(otherSpec))
            return false;
        return otherSpec instanceof MandolinSpec spec
                && style == spec.style;
    }
}