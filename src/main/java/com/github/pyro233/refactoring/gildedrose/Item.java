package com.github.pyro233.refactoring.gildedrose;

/**
 * @Author: tao.zhou
 * @Date: 2022/10/18 11:33
 */
public class Item {
    public String name;

    public int sell_in;

    public int quality;

    public Item(String name, int sell_in, int quality) {
        this.name = name;
        this.sell_in = sell_in;
        this.quality = quality;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sell_in + ", " + this.quality;
    }
}
