package com.github.pyro233.refactoring.gildedrose;

/**
 * @Author: tao.zhou
 * @Date: 2022/10/25 20:12
 */
class AgedBrie extends Item {

    public AgedBrie(final int sellIn, final int quality) {
        super("Aged Brie", sellIn, quality);
    }

    @Override
    protected void updateQuality() {
        if (quality < 50) {
            quality = quality + 1;
        }
    }

    @Override
    protected void updateQualityAfterExpiration() {
        if (quality < 50) {
            quality = quality + 1;
        }
    }
}
