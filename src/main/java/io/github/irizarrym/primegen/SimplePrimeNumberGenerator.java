package io.github.irizarrym.primegen;

import java.util.ArrayList;
import java.util.List;

public class SimplePrimeNumberGenerator implements PrimeNumberGenerator {
    @Override
    public List<Integer> generate(int startingValue, int endingValue) {
        if (startingValue > endingValue) return generate(endingValue, startingValue);
        if (startingValue < 0) startingValue = 0;
        if (endingValue < 0) return new ArrayList<>();

        List<Integer> primes = new ArrayList<>();
        for(int i = startingValue; i <= endingValue; i++) if (isPrime(i)) {
            primes.add(i);
        }
        return primes;
    }

    @Override
    public boolean isPrime(int value) {
        // All numbers less than 2 are not prime, including negative numbers.
        if (value < 2) return false;
        if (value == 2) return true; // The code after this line only fails on the number 2
        final int ceilSqrt = (int) Math.ceil(Math.sqrt(value));
        for (int m = 2; m <= ceilSqrt; m++) if (value % m == 0) return false;
        return true;
    }
}
