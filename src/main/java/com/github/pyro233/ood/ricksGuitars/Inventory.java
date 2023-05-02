package com.github.pyro233.ood.ricksGuitars;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: tao.zhou
 * @Date: 2023/5/2 17:20
 */
public class Inventory {

    private final List<Guitar> guitars;

    public Inventory() {
        guitars = new LinkedList<>();
    }

    public void addGuitar(String serialNumber, double price,
                          Builder builder, String model,
                          Type type, Wood backWood, Wood topWood) {
        Guitar guitar = new Guitar(serialNumber, price, builder,
                model, type, backWood, topWood);
        guitars.add(guitar);
    }

    public Guitar getGuitar(String serialNumber) {
        for (Guitar guitar : guitars) {
            if (guitar.getSerialNumber().equals(serialNumber)) {
                return guitar;
            }
        }
        return null;
    }

    public List<Guitar> search(GuitarSpec searchSpec) {
        List<Guitar> matchingGuitars = new ArrayList<>();
        for (Guitar guitar : guitars) {
            GuitarSpec guitarSpec = guitar.getSpec();
            if (searchSpec.builder() != guitarSpec.builder())
                continue;
            String model = searchSpec.model().toLowerCase();
            if (!model.equals("") && !model.equals(guitarSpec.model().toLowerCase()))
                continue;
            if (searchSpec.type() != guitarSpec.type())
                continue;
            if (searchSpec.backWood() != guitarSpec.backWood())
                continue;
            if (searchSpec.topWood() != guitarSpec.topWood())
                continue;
            matchingGuitars.add(guitar);
        }
        return matchingGuitars;
    }

}
