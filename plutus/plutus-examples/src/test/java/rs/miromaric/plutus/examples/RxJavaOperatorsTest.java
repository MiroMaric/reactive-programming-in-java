package rs.miromaric.plutus.examples;


import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.subscribers.TestSubscriber;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class RxJavaOperatorsTest {

    @Test
    void justRxJava() {
        TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();

        Flowable.just(1, 2, 3)
                .subscribe(testSubscriber);

        testSubscriber.assertValues(1, 2, 3);
        testSubscriber.assertComplete();
    }

    @Test
    void fromIterable() {
        TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();

        Flowable.fromIterable(List.of(1, 2, 3)).subscribe(testSubscriber);

        testSubscriber.assertValues(1, 2, 3);
        testSubscriber.assertComplete();
    }

    @Test
    void fromStream() {
        TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();

        Flowable.fromStream(Stream.of(1, 2, 3)).subscribe(testSubscriber);

        testSubscriber.assertValues(1, 2, 3);
        testSubscriber.assertComplete();
    }

    @Test
    void fromArray() {
        TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();

        Flowable.fromArray(1,2,3).subscribe(testSubscriber);

        testSubscriber.assertValues(1, 2, 3);
        testSubscriber.assertComplete();
    }

    @Test
    void from() {
        TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();

        Flowable.fromPublisher(Mono.just(1)).subscribe(testSubscriber);

        testSubscriber.assertValues(1);
        testSubscriber.assertComplete();
    }

    @Test
    void empty() {
        TestSubscriber<Object> testSubscriber = new TestSubscriber<>();

        Flowable.empty().subscribe(testSubscriber);

        testSubscriber.assertComplete();
    }

    @Test
    void error() {
        TestSubscriber<Object> testSubscriber = new TestSubscriber<>();

        Flowable.error(Exception::new).subscribe(testSubscriber);

        testSubscriber.assertError(Exception.class);
    }

    @Test
    void map() {
        TestSubscriber<Object> testSubscriber = new TestSubscriber<>();

        Flowable.fromArray(1,2,3)
                .map(MagicMethods::squere)
                .subscribe(testSubscriber);

        testSubscriber.assertValues(1, 4, 9);
        testSubscriber.assertComplete();
    }

    @Test
    void flatMap() {
        TestSubscriber<Object> testSubscriber = new TestSubscriber<>();

        Flowable.fromArray(1, 2, 3)
                .flatMap(value -> Flowable.fromStream(IntStream.rangeClosed(1, value).boxed()))
                .subscribe(testSubscriber);

        testSubscriber.assertValues(1, 1, 2, 1, 2, 3);
        testSubscriber.assertComplete();
    }

    @Test
    void startWith() {
        TestSubscriber<Object> testSubscriber = new TestSubscriber<>();

        Flowable.fromArray(1, 2, 3)
                .startWith(Maybe.just(0))
                .subscribe(testSubscriber);

        testSubscriber.assertValues(0, 1, 2, 3);
        testSubscriber.assertComplete();
    }


    void sleep(long duration) {
        try {
            Thread.sleep(duration * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
