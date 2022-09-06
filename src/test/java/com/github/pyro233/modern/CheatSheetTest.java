package com.github.pyro233.modern;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import com.github.pyro233.modern.CheatSheet.*;

/**
 * @Author: tao.zhou
 * @Date: 2022/8/25 23:23
 */
class CheatSheetTest {


    @Test
    void testRecord() {
        var c1 = new Circle(10.0);
        assertEquals(10.0, c1.radius());
        assertEquals(Math.PI * 10 * 10, c1.area());

        var c2 = new Circle(10.0);
        assertEquals(c1, c2);
    }

    @Test
    void testSealedClass() {
        Square square = new Square(10.0);
        Rectangle rectangle = new Rectangle(10.0, 5.0);
        assertTrue(Shape.isSquare(square));
        assertFalse(Shape.isSquare(rectangle));
    }
}