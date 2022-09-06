package com.github.pyro233.modern;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import com.github.pyro233.modern.CheatSheet.*;

import java.time.LocalDate;
import java.util.concurrent.Flow.*;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

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
        Shape.Square square = new Shape.Square(10.0);
        Shape.Rectangle rectangle = new Shape.Rectangle(10.0, 5.0);
        assertTrue(Shape.isSquare(square));
        assertFalse(Shape.isSquare(rectangle));
    }

    @Test
    public void testGetDaysInMonth() {
        int daysInMonth = CheatSheet.getDaysInMonth(LocalDate.of(2022, 2, 1));
        assertEquals(28, daysInMonth);
    }


    @JDK9(name = "Flow")
    @Test
    public void testTransform() throws InterruptedException {
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();
        Transform<Integer, String> transform = new Transform<>(String::valueOf);
        Subscriber<String> subscriber = new Subscriber<>() {
            private Subscription subscription;
            @Override
            public void onSubscribe(final Subscription subscription) {
                this.subscription = subscription;
                this.subscription.request(1);
            }

            @Override
            public void onNext(final String item) {
                System.out.println("receive data: " + item);
                this.subscription.request(1);
            }

            @Override
            public void onError(final Throwable throwable) {
                throwable.printStackTrace();
                this.subscription.cancel();
            }

            @Override
            public void onComplete() {
                System.out.println("completed!");
            }
        };
        publisher.subscribe(transform);
        transform.subscribe(subscriber);
        publisher.submit(8);
        publisher.close();
        TimeUnit.SECONDS.sleep(1);
    }
}