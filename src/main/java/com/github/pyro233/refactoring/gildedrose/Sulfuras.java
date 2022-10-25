package com.github.pyro233.refactoring.gildedrose;

/**
 * @Author: tao.zhou
 * @Date: 2022/10/25 20:13
 */
class Sulfuras extends Item {

    public Sulfuras(final int sellIn, final int quality) {
        super("Sulfuras, Hand of Ragnaros", sellIn, quality);
    }

    @Override
    protected boolean isSulfuras() {
        return true;
    }
}
