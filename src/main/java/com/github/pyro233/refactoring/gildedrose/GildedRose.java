package com.github.pyro233.refactoring.gildedrose;

/**
 * @Author: tao.zhou
 * @Date: 2022/10/18 11:31
 * @see <a href="https://github.com/EthanLin-TWer/gildedrose-java">林从羽</a>
 */
public class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (final Item item : items) {
            item.passOneDay();
        }
    }

}
