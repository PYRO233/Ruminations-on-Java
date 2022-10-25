package com.github.pyro233.refactoring.gildedrose;

import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @Author: tao.zhou
 * @Date: 2022/10/25 18:40
 */
public class GildedRoseSafetyNet {

    @Test
    public void should_always_generate_output_identical_to_baseline() throws IOException {
        assertThat(TextTestFixture.generateBaselineOutput(), is(BASELINE));
    }

    /**
     * resources 文件读不到，所以用了 text blocks，手动添加了 \r {@link System#lineSeparator()}
     */
    public static final String BASELINE = """
            OMGHAI!\r
            -------- day 0 --------\r
            name, sellIn, quality\r
            +5 Dexterity Vest, 10, 20\r
            Aged Brie, 2, 0\r
            Elixir of the Mongoose, 5, 7\r
            Sulfuras, Hand of Ragnaros, 0, 80\r
            Sulfuras, Hand of Ragnaros, -1, 80\r
            Backstage passes to a TAFKAL80ETC concert, 15, 20\r
            Backstage passes to a TAFKAL80ETC concert, 10, 49\r
            Backstage passes to a TAFKAL80ETC concert, 5, 49\r
            Backstage passes to a TAFKAL80ETC concert, 1, 20\r
            Conjured Mana Cake, 3, 6\r
            \r
            -------- day 1 --------\r
            name, sellIn, quality\r
            +5 Dexterity Vest, 9, 19\r
            Aged Brie, 1, 1\r
            Elixir of the Mongoose, 4, 6\r
            Sulfuras, Hand of Ragnaros, 0, 80\r
            Sulfuras, Hand of Ragnaros, -1, 80\r
            Backstage passes to a TAFKAL80ETC concert, 14, 21\r
            Backstage passes to a TAFKAL80ETC concert, 9, 50\r
            Backstage passes to a TAFKAL80ETC concert, 4, 50\r
            Backstage passes to a TAFKAL80ETC concert, 0, 23\r
            Conjured Mana Cake, 2, 5\r
            \r
            -------- day 2 --------\r
            name, sellIn, quality\r
            +5 Dexterity Vest, 8, 18\r
            Aged Brie, 0, 2\r
            Elixir of the Mongoose, 3, 5\r
            Sulfuras, Hand of Ragnaros, 0, 80\r
            Sulfuras, Hand of Ragnaros, -1, 80\r
            Backstage passes to a TAFKAL80ETC concert, 13, 22\r
            Backstage passes to a TAFKAL80ETC concert, 8, 50\r
            Backstage passes to a TAFKAL80ETC concert, 3, 50\r
            Backstage passes to a TAFKAL80ETC concert, -1, 0\r
            Conjured Mana Cake, 1, 4\r
            \r
            """;
}
