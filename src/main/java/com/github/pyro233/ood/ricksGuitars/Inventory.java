package com.github.pyro233.ood.ricksGuitars;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: tao.zhou
 * @Date: 2023/5/2 17:20
 */
public class Inventory {

    private final List<Instrument> inventory;

    public Inventory() {
        inventory = new LinkedList<>();
    }

    public void addInstrument(String serialNumber, double price,
                              InstrumentSpec spec) {
        Instrument instrument = new Instrument(serialNumber, price, spec);
        inventory.add(instrument);
    }

    public Instrument get(String serialNumber) {
        for (Instrument instrument : inventory) {
            if (instrument.getSerialNumber().equals(serialNumber)) {
                return instrument;
            }
        }
        return null;
    }

    public List<Instrument> search(InstrumentSpec instrumentSpec) {
        List<Instrument> matchingInstruments = new LinkedList<>();
        for (final Instrument instrument : inventory) {
            if (instrument.getSpec().matches(instrumentSpec))
                matchingInstruments.add(instrument);
        }
        return matchingInstruments;
    }

}
