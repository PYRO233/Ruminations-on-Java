package com.github.pyro233.ood.ricksGuitars;

/**
 * @Author: tao.zhou
 * @Date: 2023/5/2 18:23
 */
public class GuitarSpec extends InstrumentSpec {

    private int numStrings;

    public GuitarSpec(Builder builder, String model, Type type,
                      int numStrings, Wood backWood, Wood topWood) {
        super(builder, model, type, backWood, topWood);
        this.numStrings = numStrings;
    }

    public int getNumStrings() {
        return numStrings;
    }

    @Override
    public boolean matches(InstrumentSpec otherSpec) {
        if (!super.matches(otherSpec))
            return false;
        return otherSpec instanceof GuitarSpec spec
                && numStrings == spec.numStrings;
    }
}
