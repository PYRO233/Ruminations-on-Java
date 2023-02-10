package com.github.pyro233.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * On Java
 *
 * @Author: tao.zhou
 * @Date: 2023/2/10 23:15
 */
public class DelayQueueDemo {

    public static void main(String[] args) throws Exception {
        DelayQueue<DelayedTask> tasks = Stream.concat(
                        new Random(47).ints(20, 0, 4000).mapToObj(DelayedTask::new),
                        Stream.of(new DelayedTask.EndTask(4000)))
                .collect(Collectors.toCollection(DelayQueue::new));
        while (tasks.size() > 0) {
            tasks.take().run();
        }
    }

    static class DelayedTask implements Runnable, Delayed {

        private static int counter = 0;

        private final int id = counter++;
        private final int delta;
        private final long trigger;
        private static final List<DelayedTask> sequence = new ArrayList<>();

        DelayedTask(int delayInMilliseconds) {
            delta = delayInMilliseconds;
            trigger = System.nanoTime() + TimeUnit.NANOSECONDS.convert(delta, TimeUnit.MILLISECONDS);
            sequence.add(this);
        }

        @Override
        public int compareTo(final Delayed arg) {
            final DelayedTask that = (DelayedTask) arg;
//            if (this.trigger < that.trigger) return -1;
//            if (this.trigger > that.trigger) return 1;
//            return 0;
            return Long.compare(this.trigger, that.trigger);
        }

        @Override
        public void run() {
            System.out.print(this + " ");
        }

        @Override
        public long getDelay(final TimeUnit unit) {
            return unit.convert(trigger - System.nanoTime(), TimeUnit.NANOSECONDS);
        }

        @Override
        public String toString() {
            return String.format("Task [%d:%d] ", id, delta);
        }

        public String summary() {
            return String.format("(%d:%d)", id, delta);
        }

        static class EndTask extends DelayedTask {
            EndTask(final int delay) {
                super(delay);
            }

            @Override
            public void run() {
                System.out.println();
                sequence.forEach(it -> System.out.println(it.summary()));
            }
        }
    }

}
