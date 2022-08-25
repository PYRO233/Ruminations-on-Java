package com.github.pyro233;

/**
 * 1. text blocks (JDK 15)
 * 2. records (JDK 16)
 * @Author: tao.zhou
 * @Date: 2022/8/25 23:21
 */
public class CheatSheet {

    /**
     * records (JDK 16)
     */
    record Circle(double radius) {
        public double area() {
            return Math.PI * radius * radius;
        }
    }

}
