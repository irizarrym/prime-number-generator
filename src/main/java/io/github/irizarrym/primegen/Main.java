package io.github.irizarrym.primegen;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            printHelp();
            return;
        }

        // Parse command line options
        int sieveMode = 0;
        int benchmarkMode = 0;
        int noPrintMode = 0;
        int countMode = 0;
        for (String arg : args) {
            if (arg.startsWith("--")) switch (arg) {
                case "--sieve":
                    sieveMode = 1;
                    break;
                case "--sieve2":
                    sieveMode = 2;
                    break;
                case "--time":
                    benchmarkMode = 1;
                    break;
                case "--noprint":
                    noPrintMode = 1;
                    break;
                case "--count":
                    countMode = 1;
                    break;
                case "--help":
                    printHelp();
                    return;
                default:
                    System.out.println("ERROR: Unknown command line switch \"" + arg + "\"");
                    printHelp();
                    return;
            }
        }

        // Extract command line arguments
        List<Integer> bounds = new ArrayList<>();
        for (String arg : args) {
            if (arg.startsWith("--")) continue;
            try {
                bounds.add(Integer.parseInt(arg));
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Command line argument is not a valid integer \"" + arg + "\"");
                printHelp();
                return;
            }
        }

        if (bounds.size() != 2) {
            System.out.println("ERROR: Expected 2 arguments, found " + bounds.size() + ".");
            printHelp();
            return;
        }

        PrimeNumberGenerator png;
        if (sieveMode == 1) png = new SievePrimeNumberGenerator();
        else if (sieveMode == 2) png = new SegmentedSievePrimeNumberGenerator();
        else png = new SimplePrimeNumberGenerator();

        final long startTime = System.currentTimeMillis();
        List<Integer> primes = png.generate(bounds.get(0), bounds.get(1));
        final long endTime = System.currentTimeMillis();

        if (noPrintMode == 0) for (int p : primes) {
            System.out.println(p);
        }

        if (countMode == 1) {
            System.out.println(primes.size() + " prime numbers");
        }

        if (benchmarkMode == 1) {
            System.out.println((endTime - startTime) + "ms");
        }
    }

    private static final String[] HELP_TEXT = new String[]{
        "java -jar primegen.jar [OPTION] LOWER UPPER",
        "Print prime numbers from LOWER to UPPER (inclusive)",
        "",
        "Examples:",
        "  java -jar primegen.jar 0 100",
        "  java -jar primegen.jar --sieve 100 200",
        "  java -jar ./target/primegen-1.0.0.jar --noprint --time --sieve2 2000000000 2100000000",
        "",
        "Options:",
        "  --sieve       Use Sieve of Eratosthenes algorithm",
        "  --sieve2      Use segmented Sieve of Eratosthenes optimized for large LOWER and UPPER bounds",
        "  --count       Print how many prime numbers were generated",
        "  --time        Print time it took to generate primes in milliseconds",
        "  --noprint     Do not print prime numbers",
        "  --help        Print this help text"
    };

    public static void printHelp() {
        for (String line : HELP_TEXT) {
            System.out.println(line);
        }
    }
}
