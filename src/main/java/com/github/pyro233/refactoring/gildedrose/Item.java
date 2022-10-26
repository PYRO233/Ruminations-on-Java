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

    private void updateQuality() {
        if (isAgedBrie() || isBackstagePass()) {
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
        } else {
            if (quality > 0 && !isSulfuras()) {
                quality = quality - 1;
            }
        }
    }

    private void updateSellIn() {
        if (isSulfuras()) {
            return;
        }
        sellIn = sellIn - 1;
    }

    private boolean isExpired() {
        return sellIn < 0;
    }

    private void updateQualityAfterExpiration() {
        if (isAgedBrie()) {
            if (quality < 50) {
                quality = quality + 1;
            }
        } else {
            if (isBackstagePass()) {
                quality = 0;
            } else {
                if (quality > 0 && !isSulfuras()) {
                    quality = quality - 1;
                }
            }
        }
    }

    protected boolean isSulfuras() {
        return false;
    }

    protected boolean isBackstagePass() {
        return false;
    }

    protected boolean isAgedBrie() {
        return false;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }
}