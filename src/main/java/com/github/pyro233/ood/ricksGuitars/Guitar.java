package com.github.pyro233.ood.ricksGuitars;

/**
 * @Author: tao.zhou
 * @Date: 2023/5/2 17:17
 */
public class Guitar {

    private final String serialNumber;
    private double price;
    private final GuitarSpec spec;

    public Guitar(String serialNumber, double price,
                  Builder builder, String model, Type type,
                  Wood backWood, Wood topWood) {
        this.serialNumber = serialNumber;
        this.price = price;
        this.spec = new GuitarSpec(builder, model, type, backWood, topWood);
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float newPrice) {
        this.price = newPrice;
    }

    public GuitarSpec getSpec() {
        return spec;
    }
}
