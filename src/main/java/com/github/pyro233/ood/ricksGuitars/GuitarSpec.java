package com.github.pyro233.ood.ricksGuitars;

/**
 * @Author: tao.zhou
 * @Date: 2023/5/2 18:23
 */
public record GuitarSpec(Builder builder, String model, Type type, int numStrings, Wood backWood, Wood topWood) {

    public boolean matches(final GuitarSpec searchSpec) {
        if (searchSpec.builder() != this.builder())
            return false;
        String model = searchSpec.model().toLowerCase();
        if (!model.equals("") && !model.equals(this.model().toLowerCase()))
            return false;
        if (searchSpec.type() != this.type())
            return false;
        if (searchSpec.backWood() != this.backWood())
            return false;
        if (searchSpec.topWood() != this.topWood())
            return false;
        if (searchSpec.numStrings() != this.numStrings())
            return false;
        return true;
    }

}
