package rs.miromaric.plutus.examples;


import io.reactivex.rxjava3.core.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.stream.IntStream;

class CodeSnippetsTests {

    @Test
    void stream() {
        int response = IntStream.rangeClosed(1, 100)
                .filter(MagicMethods::isOdd)
                .filter(MagicMethods::isPrime)
                .map(MagicMethods::squere)
                .reduce(Integer::sum)
                .orElseThrow();

        Assertions.assertEquals(65792, response);
    }

    @Test
    void flux() {
        Mono<Integer> response = Flux.range(1, 100)
                .filter(MagicMethods::isOdd)
                .filter(MagicMethods::isPrime)
                .map(MagicMethods::squere)
                .reduce(Integer::sum);

        StepVerifier.create(response)
                .expectNext(65792)
                .verifyComplete();
    }

    //@Test
    //void flowable() {
    //    Flowable.just(1);
    //    Observable.just(1);
    //    Single.just(1);
    //    Completable.complete();
    //    Maybe.just(1);
    //}

}
