package com.github.pyro233.ood.ricksGuitars;

import java.util.List;
import java.util.Map;

/**
 * Head First Object-Oriented Analysis and Design
 * https://github.com/XFWong/Head-First-Object-Oriented-Analysis-and-Design
 * todo refactor this class using junit5
 *
 * @Author: tao.zhou
 * @Date: 2023/5/2 17:23
 */
public class FindInstrumentTester {

    public static void main(String[] args) {
        // Set up Rick's guitar inventory
        Inventory inventory = new Inventory();
        initializeInventory(inventory);

        Map<String, Object> properties = Map.of("builder", Builder.GIBSON, "backWood", Wood.MAPLE);
        InstrumentSpec whatBryanLikes = new InstrumentSpec(properties);
        List<Instrument> matchingInstruments = inventory.search(whatBryanLikes);
        if (!matchingInstruments.isEmpty()) {
            System.out.println("Bryan, you might like these instruments:");
            for (Instrument instrument : matchingInstruments) {
                InstrumentSpec spec = instrument.getSpec();
                System.out.println("We have a " + spec.getProperty("instrumentType") + " with the following properties:");
                for (final String propertyName : spec.getProperties().keySet()) {
                    if (propertyName.equals("instrumentType"))
                        continue;
                    System.out.println("    " + propertyName + ": " + spec.getProperty(propertyName));
                }
                System.out.println("  You can have this " + spec.getProperty("instrumentType") + " for $" +
                        instrument.getPrice() + "\n---");
            }
        } else {
            System.out.println("Sorry, Bryan, we have nothing for you.");
        }
    }

    private static void initializeInventory(Inventory inventory) {
        Map<String, Object> properties = Map.of("instrumentType", InstrumentType.GUITAR,
                "builder", Builder.COLLINGS,
                "model", "CJ",
                "type", Type.ACOUSTIC,
                "numStrings", 6,
                "topWood", Wood.INDIAN_ROSEWOOD,
                "backWood", Wood.SITKA);
        inventory.addInstrument("11277", 3999.95, new InstrumentSpec(properties));

        properties = Map.of("instrumentType", InstrumentType.GUITAR,
                "builder", Builder.MARTIN,
                "model", "D-18",
                "type", Type.ACOUSTIC,
                "numStrings", 6,
                "topWood", Wood.MAHOGANY,
                "backWood", Wood.ADIRONDACK);
        inventory.addInstrument("122784", 5495.95, new InstrumentSpec(properties));

        properties = Map.of("instrumentType", InstrumentType.GUITAR,
                "builder", Builder.FENDER,
                "model", "Stratocastor",
                "type", Type.ELECTRIC,
                "numStrings", 6,
                "topWood", Wood.ALDER,
                "backWood", Wood.ALDER);
        inventory.addInstrument("V95693", 1499.95, new InstrumentSpec(properties));
        inventory.addInstrument("V9512", 1549.95, new InstrumentSpec(properties));

        properties = Map.of("instrumentType", InstrumentType.GUITAR,
                "builder", Builder.GIBSON,
                "model", "Les Paul",
                "type", Type.ELECTRIC,
                "numStrings", 6,
                "topWood", Wood.MAPLE,
                "backWood", Wood.MAPLE);
        inventory.addInstrument("70108276", 2295.95, new InstrumentSpec(properties));

        properties = Map.of("instrumentType", InstrumentType.GUITAR,
                "builder", Builder.GIBSON,
                "model", "SG '61 Reissue",
                "type", Type.ELECTRIC,
                "numStrings", 6,
                "topWood", Wood.MAHOGANY,
                "backWood", Wood.MAHOGANY);
        inventory.addInstrument("82765501", 1890.95, new InstrumentSpec(properties));

        properties = Map.of("instrumentType", InstrumentType.MANDOLIN,
                "builder", Builder.GIBSON,
                "model", "F-5G",
                "type", Type.ACOUSTIC,
                "topWood", Wood.MAPLE,
                "backWood", Wood.MAPLE);
        inventory.addInstrument("9019920", 5495.99, new InstrumentSpec(properties));

        properties = Map.of("instrumentType", InstrumentType.BANJO,
                "builder", Builder.GIBSON,
                "model", "RB-3 Wreath",
                "type", Type.ACOUSTIC,
                "numStrings", 5,
                "backWood", Wood.MAPLE);
        inventory.addInstrument("8900231", 2945.95, new InstrumentSpec(properties));
    }

}
