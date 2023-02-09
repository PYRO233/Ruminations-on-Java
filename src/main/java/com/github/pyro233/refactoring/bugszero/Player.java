package com.github.pyro233.refactoring.bugszero;

/**
 * @Author: tao.zhou
 * @Date: 2023/2/9 16:28
 */
public class Player {
    private final String name;
    private int goldCoins = 0;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getGoldCoins() {
        return goldCoins;
    }

    public void increaseGoldCoin() {
        goldCoins += 1;
    }
}
