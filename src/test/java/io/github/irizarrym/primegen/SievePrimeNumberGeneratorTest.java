package io.github.irizarrym.primegen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SievePrimeNumberGeneratorTest {
    private final PrimeNumberGenerator png;

    public SievePrimeNumberGeneratorTest() {
        png = new SievePrimeNumberGenerator();
    }

    @Test
    public void pngIsSieveInstance() {
        Assertions.assertTrue(png instanceof SievePrimeNumberGenerator);
    }

    @Test
    public void generate0to100() {
        List<Integer> expected = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97);
        List<Integer> actual = png.generate(0, 100);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void generate100to0() {
        List<Integer> expected = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97);
        List<Integer> actual = png.generate(100, 0);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void generate7900to7920() {
        List<Integer> expected = Arrays.asList(7901, 7907, 7919);
        List<Integer> actual = png.generate(7900, 7920);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void generateAllRangesUpTo100() {
        PrimeNumberGenerator simple = new SimplePrimeNumberGenerator();
        for (int a = -100; a <= 100; a++) for (int b = -100; b <= 100; b++) {
            Assertions.assertEquals(simple.generate(a, b), png.generate(a, b));
        }
    }

    @Test
    public void isPrime0to100() {
        Set<Integer> primes = new HashSet<>(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97));
        for (int i = 0; i <= 100; i++) {
            final boolean expected = primes.contains(i);
            final boolean actual = png.isPrime(i);
            Assertions.assertEquals(expected, actual, Integer.toString(i));
        }
    }

    @Test
    public void isPrime7900to7920() {
        Set<Integer> primes = new HashSet<>(Arrays.asList(7901, 7907, 7919));
        for (int i = 7900; i <= 7920; i++) {
            final boolean expected = primes.contains(i);
            final boolean actual = png.isPrime(i);
            Assertions.assertEquals(expected, actual, "Number tested: " + Integer.toString(i));
        }
    }
}
