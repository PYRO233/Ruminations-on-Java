package com.github.pyro233.refactoring.bugszero;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @Author: tao.zhou
 * @Date: 2022/10/31 11:11
 */
class GameTest {
    @Test
    public void itsLockedDown() throws Exception {
        Random randomizer = new Random(123455);
        ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(resultStream));

        IntStream.range(1, 15).forEach(i -> GameRunner.playGame(randomizer));

        Approvals.verify(resultStream.toString());
    }
}