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
        Instrument instrument = null;
        if (spec instanceof GuitarSpec) {
            instrument = new Guitar(serialNumber, price, (GuitarSpec)spec);
        } else if (spec instanceof MandolinSpec) {
            instrument = new Mandolin(serialNumber, price, (MandolinSpec)spec);
        }
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

    public List<Guitar> search(GuitarSpec searchSpec) {
        List<Guitar> matchingGuitars = new LinkedList<>();
        for (final Instrument instrument : inventory) {
            if (instrument.getSpec().matches(searchSpec))
                matchingGuitars.add((Guitar) instrument);
        }
        return matchingGuitars;
    }

    public List<Mandolin> search(MandolinSpec searchSpec) {
        List<Mandolin> matchingMandolins = new LinkedList<>();
        for (final Instrument instrument : inventory) {
            if (instrument.getSpec().matches(searchSpec))
                matchingMandolins.add((Mandolin) instrument);
        }
        return matchingMandolins;
    }

}
