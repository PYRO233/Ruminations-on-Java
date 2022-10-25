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

    static Item createAgedBrie(final int sellIn, final int quality) {
        return new AgedBrie(sellIn, quality);
    }

    static Item createSulfuras(final int sellIn, final int quality) {
        return new Sulfuras(sellIn, quality);
    }

    static Item createBackstagePass(final int sellIn, final int quality) {
        return new BackstagePass(sellIn, quality);
    }

    void updateQuality() {
        if (!isAgedBrie()
                && !isBackstagePass()) {
            if (quality > 0) {
                if (!isSulfuras()) {
                    quality = quality - 1;
                }
            }
        } else {
            if (quality < 50) {
                quality = quality + 1;

                if (isBackstagePass()) {
                    if (sellIn < 11) {
                        if (quality < 50) {
                            quality = quality + 1;
                        }
                    }

                    if (sellIn < 6) {
                        if (quality < 50) {
                            quality = quality + 1;
                        }
                    }
                }
            }
        }

        if (!isSulfuras()) {
            sellIn = sellIn - 1;
        }

        if (sellIn < 0) {
            if (!isAgedBrie()) {
                if (!isBackstagePass()) {
                    if (quality > 0) {
                        if (!isSulfuras()) {
                            quality = quality - 1;
                        }
                    }
                } else {
                    quality = 0;
                }
            } else {
                if (quality < 50) {
                    quality = quality + 1;
                }
            }
        }
    }

    private boolean isSulfuras() {
        return name.equals("Sulfuras, Hand of Ragnaros");
    }

    private boolean isBackstagePass() {
        return name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private boolean isAgedBrie() {
        return name.equals("Aged Brie");
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }
}

class AgedBrie extends Item {

    public AgedBrie(final int sellIn, final int quality) {
        super("Aged Brie", sellIn, quality);
    }
}

class Sulfuras extends Item {

    public Sulfuras(final int sellIn, final int quality) {
        super("Sulfuras, Hand of Ragnaros", sellIn, quality);
    }
}

class BackstagePass extends Item {

    public BackstagePass(final int sellIn, final int quality) {
        super("Backstage passes to a TAFKAL80ETC concert", sellIn, quality);
    }
}
