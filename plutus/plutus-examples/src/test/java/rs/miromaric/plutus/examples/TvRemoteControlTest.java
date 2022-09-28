package rs.miromaric.plutus.examples;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

class TvRemoteControlTest {

    @Test
    void test() {
        Flux<Integer> flux = digitsStream()
                .elapsed()
                .bufferUntilChanged(Tuple2::getT1, this::checkPressTime)
                .map(this::mapToDigits)
                .flatMap(this::getChannelNumber)
                .filter(channel -> !blockedChannels().contains(channel))
                .doOnNext(System.out::println);

        StepVerifier.create(flux)
                .expectNextCount(3)
                .verifyComplete();
    }

    private List<Integer> mapToDigits(List<Tuple2<Long, Integer>> tuple2s) {
        return tuple2s.stream().map(Tuple2::getT2).collect(Collectors.toList());
    }

    private boolean checkPressTime(Long aLong, Long aLong2) {
        return aLong2 - aLong < 500;
    }

    private Mono<Integer> getChannelNumber(List<Integer> digits) {
        return Flux.fromIterable(digits)
                .skipUntil(integer -> integer != 0)
                .map(String::valueOf)
                .reduce(String::concat)
                .map(Integer::valueOf);
    }

    private Flux<Integer> digitsStream() {
        return Flux.just(
                        Mono.just(0).delayElement(Duration.ofMillis(0)),
                        Mono.just(1).delayElement(Duration.ofMillis(0)),
                        Mono.just(5).delayElement(Duration.ofMillis(0)),
                        Mono.just(0).delayElement(Duration.ofMillis(0)),
                        Mono.just(0).delayElement(Duration.ofMillis(0)),
                        Mono.just(1).delayElement(Duration.ofMillis(0)),
                        Mono.just(1).delayElement(Duration.ofMillis(2000)),
                        Mono.just(2).delayElement(Duration.ofMillis(2400)),
                        Mono.just(5).delayElement(Duration.ofMillis(2620)),
                        Mono.just(5).delayElement(Duration.ofMillis(5620)),
                        Mono.just(5).delayElement(Duration.ofMillis(5820)),
                        Mono.just(5).delayElement(Duration.ofMillis(6100)))
                .flatMap(Function.identity());
    }

    private List<Integer> blockedChannels() {
        return List.of(15, 22, 36, 73);
    }

}
