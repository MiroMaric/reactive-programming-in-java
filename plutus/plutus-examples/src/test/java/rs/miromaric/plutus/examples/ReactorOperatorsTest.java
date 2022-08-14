package rs.miromaric.plutus.examples;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class ReactorOperatorsTest {

    @Test
    void justReactor() {
        Flux<Integer> flux = Flux.just(1, 2, 3);

        StepVerifier.create(flux)
                .expectNext(1, 2, 3)
                .expectComplete()
                .verify();
    }

    @Test
    void fromIterable() {
        Flux<Integer> flux = Flux.fromIterable(List.of(1, 2, 3));

        StepVerifier.create(flux)
                .expectNext(1, 2, 3)
                .expectComplete()
                .verify();
    }

    @Test
    void fromStream() {
        Flux<Integer> flux = Flux.fromStream(Stream.of(1, 2, 3));

        StepVerifier.create(flux)
                .expectNext(1, 2, 3)
                .expectComplete()
                .verify();
    }

    @Test
    void from() {
        Flux<Integer> flux = Flux.from(Mono.just(1));

        StepVerifier.create(flux)
                .expectNext(1)
                .expectComplete()
                .verify();
    }

    @Test
    void fromArray() {
        Flux<Integer> flux = Flux.fromArray(new Integer[] {1,2,3});

        StepVerifier.create(flux)
                .expectNext(1, 2, 3)
                .expectComplete()
                .verify();
    }

    @Test
    void empty() {
        Flux<Integer> flux = Flux.empty();

        StepVerifier.create(flux)
                .expectComplete()
                .verify();
    }

    @Test
    void error() {
        Flux<Integer> flux = Flux.error(Exception::new);

        StepVerifier.create(flux)
                .expectError()
                .verify();
    }

    @Test
    void map() {
        Flux<Integer> flux = Flux.just(1, 2, 3)
                .map(MagicMethods::squere);

        StepVerifier.create(flux)
                .expectNext(1, 4, 9)
                .expectComplete()
                .verify();
    }

    @Test
    void flatMap() {
        Flux<Integer> flux = Flux.just(1, 2, 3)
                .flatMap(value -> Flux.fromStream(IntStream.rangeClosed(1, value).boxed()));

        StepVerifier.create(flux)
                .expectNext(1, 1, 2, 1,2,3)
                .expectComplete()
                .verify();
    }

    @Test
    void startWith() {
        Flux<Integer> flux = Flux.just(1, 2, 3).startWith(0);

        StepVerifier.create(flux)
                .expectNext(0, 1,2,3)
                .expectComplete()
                .verify();
    }

    @Test
    void collectList() {
        Mono<List<Integer>> mono = Flux.just(1, 2, 3).collectList();

        StepVerifier.create(mono)
                .expectNext(List.of(1,2,3))
                .expectComplete()
                .verify();
    }

    @Test
    void all() {
        Mono<Boolean> mono = Flux.just(1, 2, 3).all(number -> number < 5);

        StepVerifier.create(mono)
                .expectNext(true)
                .expectComplete()
                .verify();
    }

    @Test
    void concatWith() {
        Flux<Integer> flux = Flux.just(1, 2, 3)
                .delayElements(Duration.ofMillis(300))
                .concatWith(Flux.just(4, 5));

        StepVerifier.create(flux)
                .expectNext(1, 2, 3, 4, 5)
                .expectComplete()
                .verify();
    }

    @Test
    void mergeWith() {
        Flux<Integer> flux = Flux.just(1, 2, 3)
                .delayElements(Duration.ofMillis(300))
                .mergeWith(Flux.just(4, 5));

        StepVerifier.create(flux)
                .expectNext(4,5, 1, 2, 3)
                .expectComplete()
                .verify();
    }

    @Test
    void zip() {
        Flux<Integer> numbers = Flux.just(1, 2);
        Flux<String> strings = Flux.just("1", "2");
        Flux<String> zip = Flux.zip(numbers, strings).map(tuple -> tuple.getT2() + tuple.getT1());
        StepVerifier.create(zip)
                .expectNext("11", "22")
                .expectComplete()
                .verify();
    }

    @Test
    void firstWithValue() {
        Flux<Integer> flux1 = Flux.just(1, 2).delayElements(Duration.ofSeconds(1));
        Flux<Integer> flux2 = Flux.just(3, 4);
        Flux<Integer> firstFlux = Flux.firstWithValue(flux1, flux2);
        StepVerifier.create(firstFlux)
                .expectNext(3, 4)
                .expectComplete()
                .verify();
    }

    @Test
    void switchIfEmpty() {
        Flux<Integer> flux = Flux.just(1, 2, 3).filter(number -> number > 5);
        Flux<Integer> result = flux.switchIfEmpty(Flux.just(4, 5));
        StepVerifier.create(result)
                .expectNext(4, 5)
                .expectComplete()
                .verify();
    }

    @Test
    void doOnSubscribe() {
        AtomicInteger result = new AtomicInteger();
        Mono<Integer> mono = Mono.just(0).doOnSubscribe(subscription -> result.incrementAndGet());
        mono.subscribe();
        mono.subscribe();
        StepVerifier.create(mono)
                .expectNext(0)
                .expectComplete()
                .verify();
        Assertions.assertEquals(3, result.intValue());
    }

    @Test
    void doOnNext() {
        AtomicInteger result = new AtomicInteger();
        Flux<Integer> flux = Flux.just(1, 2, 3).doOnNext(result::addAndGet);
        StepVerifier.create(flux).expectNextCount(3).verifyComplete();
        Assertions.assertEquals(6, result.intValue());
    }

    @Test
    void doOnError() {
        AtomicBoolean error = new AtomicBoolean();
        Flux<?> flux = Flux.just(1, 2, 3)
                .skip(2)
                .flatMap(ignore -> Mono.error(RuntimeException::new))
                .doOnError(throwable -> error.set(true));
        StepVerifier.create(flux).expectError().verify();
        Assertions.assertTrue(error.get());
    }

    @Test
    void log() {
        Flux<Integer> flux = Flux.just(1,2,3).log();
        StepVerifier.create(flux)
                .expectNextCount(3)
                .verifyComplete();
    }


}
