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

        boolean[] sieve = generateSieve(endingValue + 1);

        // Collect prime numbers into list
        List<Integer> primes = new ArrayList<>();
        for (int i = startingValue; i <= endingValue; i++) {
            if (sieve[i]) primes.add(i);
        }

        return primes;
    }

    public boolean[] generateSieve(int size) {
        switch (size) {
            case 0:
                return new boolean[]{false};
            case 1:
                return new boolean[]{false, false};
            case 2:
                return new boolean[]{false, false, true};
            default:
                break;
        }

        // This is the array for the sieve
        boolean[] isPrime = new boolean[size];
        Arrays.fill(isPrime, true);

        // Preset first few values
        isPrime[0] = false;
        isPrime[1] = false;
        isPrime[2] = true;

        final int ceilSqrt = (int) Math.ceil(Math.sqrt(size));

        // Mark composite values as false
        for (int p = 2; p < ceilSqrt; p++) {
            if (!isPrime[p]) continue; // skip non-prime numbers
            for (int i = p * p; i < isPrime.length; i += p) {
                isPrime[i] = false;
            }
        }

        return isPrime;
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
