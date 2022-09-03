package rs.miromaric.plutus.examples;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    @Test
    void imperative() {
        int[] numbers = {1, 19, 3, 14, 5, 9, 16, 54, 19, 70};
        int sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            int number = numbers[i];
            if (number % 2 == 1) {
                int square = number * number;
                sum += square;
            }
        }

        Assertions.assertEquals(838, sum);
    }

    @Test
    void procedure() {
        int[] numbers = {1, 19, 3, 14, 5, 9, 16, 54, 19, 70};
        int sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            int number = numbers[i];
            if (isOdd(number)) {
                sum += square(number);
            }
        }

        Assertions.assertEquals(838, sum);
    }

    int square(int number) {
        return number * number;
    }

    boolean isOdd(int number) {
        return number % 2 == 1;
    }

    @Test
    void objectOriented() {
        class MagicNumbers {
            private final int[] numbers;

            public MagicNumbers(int[] numbers) {
                this.numbers = numbers;
            }

            int sumOfSquaresOfOddNumbers() {
                int sum = 0;
                for (int i = 0; i < numbers.length; i++) {
                    int number = numbers[i];
                    if (isOdd(number)) {
                        sum += square(number);
                    }
                }
                return sum;
            }

            public static int square(int number) {
                return number * number;
            }

            public static boolean isOdd(int number) {
                return number % 2 == 1;
            }
        }

        MagicNumbers magicNumbers = new MagicNumbers(new int[]{1, 19, 3, 14, 5, 9, 16, 54, 19, 70});
        int sum = magicNumbers.sumOfSquaresOfOddNumbers();

        Assertions.assertEquals(838, sum);
    }

    @Test
    void functional() {
        Integer sum = Stream
                .of(1, 19, 3, 14, 5, 9, 16, 54, 19, 70)
                .filter(this::isOdd)
                .map(this::square)
                .reduce(0, Integer::sum);

        Assertions.assertEquals(838, sum);
    }

    @Test
    void reactive() {
        Mono<Integer> sum = Flux
                .just(1, 19, 3, 14, 5, 9, 16, 54, 19, 70)
                .filter(this::isOdd)
                .map(this::square)
                .reduce(0, Integer::sum)
                .doOnNext(System.out::println);

        StepVerifier.create(sum)
                .expectNext(838)
                .verifyComplete();
    }
}
