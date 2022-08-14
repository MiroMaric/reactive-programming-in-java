package rs.miromaric.plutus.examples;

import java.util.stream.IntStream;

class MagicMethods {

    static boolean isPrime(int n) {
        // Corner case
        if (n <= 1)
            return false;

        // Check from 2 to n-1
        for (int i = 2; i < n; i++)
            if (n % i == 0)
                return false;

        return true;
    }

    static boolean isOdd(int value) {
        return  value % 2 == 1;
    }

    static int squere(int value) {
        return value * value;
    }

}

