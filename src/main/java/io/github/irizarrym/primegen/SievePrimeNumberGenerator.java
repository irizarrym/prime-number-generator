package io.github.irizarrym.primegen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Generates a list of primes using the Sieve of Eratosthenes
 */
public class SievePrimeNumberGenerator implements PrimeNumberGenerator {
    @Override
    public List<Integer> generate(int startingValue, int endingValue) {
        if (startingValue > endingValue) return generate(endingValue, startingValue);
        if (startingValue < 0) startingValue = 0;
        if (endingValue < 2) return new ArrayList<>();

        // This is the array for the sieve
        boolean[] isPrime = new boolean[endingValue + 1];
        Arrays.fill(isPrime, true);

        // Preset first few values
        isPrime[0] = false;
        isPrime[1] = false;
        isPrime[2] = true;

        // Only need to run up to square root of upper bound
        final int ceilSqrt = (int) Math.ceil(Math.sqrt(endingValue));

        // Mark all composite values
        for (int p = 2; p <= ceilSqrt; p++) {
            // skip composite values
            if (!isPrime[p]) continue;
            for (int i = p + p; i <= endingValue; i += p) {
                isPrime[i] = false;
            }
        }

        // Collect prime numbers into list
        List<Integer> primes = new ArrayList<>();
        for (int i = startingValue; i <= endingValue; i++) {
            if (isPrime[i]) primes.add(i);
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
