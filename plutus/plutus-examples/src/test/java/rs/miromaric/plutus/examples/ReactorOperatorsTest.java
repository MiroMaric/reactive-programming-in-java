package rs.miromaric.plutus.examples;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Timed;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class ReactorOperatorsTest {

    @Test
    void creationOperators() {
        Integer[] numbers = {1, 2, 3};

        Flux<Integer> just = Flux.just(numbers);
        StepVerifier.create(just).expectNext(numbers).verifyComplete();

        Flux<Integer> fromIterable = Flux.fromIterable(List.of(numbers));
        StepVerifier.create(fromIterable).expectNext(numbers).verifyComplete();

        Flux<Integer> fromStream = Flux.fromStream(Stream.of(numbers));
        StepVerifier.create(fromStream).expectNext(numbers).verifyComplete();

        Flux<Integer> fromArray = Flux.fromArray(numbers);
        StepVerifier.create(fromArray).expectNext(numbers).verifyComplete();

        Flux<Integer> range = Flux.range(1, 3);
        StepVerifier.create(range).expectNext(numbers).verifyComplete();

        Flux<Integer> from = Flux.from(Mono.just(1));
        StepVerifier.create(from).expectNext(1).verifyComplete();

        Flux<Integer> empty = Flux.empty();
        StepVerifier.create(empty).verifyComplete();

        Flux<Integer> error = Flux.error(Exception::new);
        StepVerifier.create(error).expectError().verify();
    }

    @Test
    void transformOperators() {
        Integer[] numbers = {1, 2, 3};

        Flux<Integer> map = Flux.just(numbers).map(MagicMethods::squere);
        StepVerifier.create(map).expectNext(1, 4, 9).verifyComplete();

        Flux<Integer> flatMap = Flux.just(numbers)
                .flatMap(value -> Flux.fromStream(IntStream.rangeClosed(1, value).boxed()));
        StepVerifier.create(flatMap).expectNext(1, 1, 2, 1,2,3).verifyComplete();

        Flux<Integer> startWith = Flux.just(numbers).startWith(0);
        StepVerifier.create(startWith).expectNext(0, 1,2,3).verifyComplete();

        Mono<List<Integer>> collectList = Flux.just(numbers).collectList();
        StepVerifier.create(collectList).expectNext(List.of(numbers)).verifyComplete();

        Mono<Boolean> all = Flux.just(numbers).all(number -> number < 5);
        StepVerifier.create(all).expectNext(true).verifyComplete();

        Flux<Integer> concatWith = Flux.just(numbers)
                .delayElements(Duration.ofMillis(300))
                .concatWith(Flux.just(4, 5));
        StepVerifier.create(concatWith).expectNext(1, 2, 3, 4, 5).verifyComplete();

        Flux<Integer> mergeWith = Flux.just(numbers)
                .delayElements(Duration.ofMillis(300))
                .mergeWith(Flux.just(4, 5));
        StepVerifier.create(mergeWith).expectNext(4,5, 1, 2, 3).verifyComplete();

        Flux<Integer> zip = Flux.zip(Flux.just(numbers), Flux.range(4,3))
                .map(tuple -> tuple.getT2() + tuple.getT1());
        StepVerifier.create(zip).expectNext(5,7,9).verifyComplete();

        Flux<Integer> firstWithValue = Flux.firstWithValue(
                Flux.just(1, 2).delayElements(Duration.ofSeconds(1)),
                Flux.just(3, 4));
        StepVerifier.create(firstWithValue).expectNext(3, 4).verifyComplete();

        Flux<Integer> switchIfEmpty = Flux.just(numbers).filter(number -> number > 5)
                .switchIfEmpty(Flux.just(4, 5));
        StepVerifier.create(switchIfEmpty).expectNext(4, 5).verifyComplete();
    }

    @Test
    void peepOperators() {
        Integer[] numbers = {1, 2, 3};

        AtomicInteger result = new AtomicInteger();
        Mono.just(0).doOnSubscribe(subscription -> result.incrementAndGet()).subscribe();
        Assertions.assertEquals(1, result.intValue());

        result.set(0);
        Flux.just(numbers).doOnNext(result::addAndGet).subscribe();
        Assertions.assertEquals(6, result.intValue());

        AtomicBoolean error = new AtomicBoolean();
        Flux.just(numbers)
                .skip(2)
                .flatMap(ignore -> Mono.error(RuntimeException::new))
                .doOnError(throwable -> error.set(true))
                .subscribe();
        Assertions.assertTrue(error.get());

        Flux<Integer> flux = Flux.just(numbers).log();
        StepVerifier.create(flux).expectNextCount(3).verifyComplete();
    }

    @Test
    void filterOperators() {
        Flux<Integer> filter = Flux.range(1, 10).filter(MagicMethods::isPrime);
        StepVerifier.create(filter).expectNext(2,3,5, 7).verifyComplete();

        Flux<Integer> distinct = Flux.just(1, 2, 3, 2, 2, 1).distinct();
        StepVerifier.create(distinct).expectNext(1, 2, 3).verifyComplete();

        Flux<Integer> take = Flux.range(1, 100).take(5);
        StepVerifier.create(take).expectNext(1, 2, 3, 4, 5).verifyComplete();

        Flux<Integer> skip = Flux.range(1, 10).skip(5);
        StepVerifier.create(skip).expectNext(6, 7, 8, 9, 10).verifyComplete();
    }

    @Test
    void handleErrorOperators() {
        Integer[] numbers = {1, 2, 3};

        Flux<Integer> onErrorReturn = Flux.just(numbers)
                .concatWith(Flux.error(IllegalArgumentException::new))
                .onErrorReturn(IllegalArgumentException.class, 4);
        StepVerifier.create(onErrorReturn).expectNext(1, 2, 3, 4).verifyComplete();

        Flux<Integer> onErrorResume = Flux.just(numbers)
                .concatWith(Flux.error(Exception::new))
                .onErrorResume(e -> Flux.just(4, 5, 6));
        StepVerifier.create(onErrorResume).expectNext(1, 2, 3, 4, 5, 6).verifyComplete();

        Flux<Integer> onErrorMap = Flux.just(numbers)
                .concatWith(Flux.error(IllegalArgumentException::new))
                .onErrorMap(IllegalArgumentException.class, e -> new IllegalStateException());
        StepVerifier.create(onErrorMap).expectNext(numbers).expectError().verify();

        AtomicBoolean finallyExecuted = new AtomicBoolean();
        Flux<Integer> doFinally = Flux.just(numbers)
                .concatWith(Mono.error(Exception::new))
                .doFinally(signalType -> finallyExecuted.set(true));
        StepVerifier.create(doFinally).expectNext(numbers).expectError().verify();
        Assertions.assertTrue(finallyExecuted.get());

        AtomicBoolean cleanupExecuted = new AtomicBoolean();

        Flux<Integer> using = Flux.using(
                () -> "ignore",
                file -> Flux.just(numbers),
                file -> cleanupExecuted.set(true));
        StepVerifier.create(using).expectNext(numbers).verifyComplete();
        Assertions.assertTrue(cleanupExecuted.get());
    }


    @Test
    void timeOperators() {
        Integer[] numbers = {1, 2, 3};

        Flux<Integer> delayElements = Flux.just(numbers).delayElements(Duration.ofSeconds(1));
        StepVerifier.create(delayElements).expectNextCount(3).verifyComplete();

        Flux<Integer> timeout = Flux.just(numbers)
                .delayElements(Duration.ofSeconds(1))
                .mergeWith(Flux.just(4).delayElements(Duration.ofSeconds(6)))
                .timeout(Duration.ofSeconds(2));
        StepVerifier.create(timeout).expectNextCount(3).expectError().verify();

        Flux<Timed<Integer>> timed = Flux.just(numbers).delayElements(Duration.ofSeconds(1)).timed();
        StepVerifier.create(timed).expectNextCount(3).verifyComplete();

        Flux<Long> flux = Flux.interval(Duration.ofSeconds(1)).take(3);
        StepVerifier.create(flux).expectNext(0L, 1L, 2L).verifyComplete();
    }

    @Test
    void scan() {
        Flux<Integer> mono = Flux.just("a", "b", "c")
                .map(s -> 1)
                .scan(0, Integer::sum);

        StepVerifier.create(mono)
                .expectNext(0,1,2,3)
                .verifyComplete();
    }

    @Test
    void reduce() {
        Mono<Integer> flux = Flux.just("a", "b", "c")
                .map(s -> 1)
                .reduce(0, Integer::sum);

        StepVerifier.create(flux)
                .expectNext(3)
                .verifyComplete();
    }

    @Test
    void splitOperators() {
        Flux<Flux<Integer>> window = Flux.range(1, 10).window(3);
        StepVerifier.create(window).expectNextCount(4).verifyComplete();

        Flux<Flux<Integer>> windowDuration = Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                .window(Duration.ofSeconds(3));
        StepVerifier.create(windowDuration).expectNextCount(4).verifyComplete();

        Flux<List<Integer>> buffer = Flux.range(1, 10).buffer(3);
        StepVerifier.create(buffer).expectNextCount(4).verifyComplete();

        Flux<List<Integer>> bufferDuration = Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                .buffer(Duration.ofSeconds(3));
        StepVerifier.create(bufferDuration).expectNextCount(4).verifyComplete();
    }
}
