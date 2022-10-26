package com.github.pyro233.refactoring.gildedrose;

/**
 * @Author: tao.zhou
 * @Date: 2022/10/18 11:33
 */
public class Item {
    public String name;

    public int sellIn;

    public int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    void passOneDay() {
        updateQuality();

        updateSellIn();

        if (isExpired()) {
            updateQualityAfterExpiration();
        }
    }

    protected void updateQuality() {
        if (quality > 0) {
            quality = quality - 1;
        }
    }

    protected void updateSellIn() {
        sellIn = sellIn - 1;
    }

    private boolean isExpired() {
        return sellIn < 0;
    }

    protected void updateQualityAfterExpiration() {
        if (quality > 0) {
            quality = quality - 1;
        }
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }
}