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
                          GuitarSpec spec) {
        Guitar guitar = new Guitar(serialNumber, price, spec);
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
            if (guitarSpec.matches(searchSpec)) matchingGuitars.add(guitar);
        }
        return matchingGuitars;
    }

}
