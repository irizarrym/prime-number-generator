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
        boolean useSieve = false;
        for (String arg : args) {
            if (arg.startsWith("--")) switch (arg) {
                case "--sieve":
                    useSieve = true;
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
        if (useSieve) png = new SievePrimeNumberGenerator();
        else png = new SimplePrimeNumberGenerator();

        List<Integer> primes = png.generate(bounds.get(0), bounds.get(1));
        for (int p : primes) {
            System.out.println(p);
        }
    }

    private static final String[] HELP_TEXT = new String[]{
        "java -jar primegen.jar [OPTION] LOWER UPPER",
        "Print prime numbers from LOWER to UPPER (inclusive)",
        "",
        "Examples:",
        "  java -jar primegen.jar 0 100",
        "  java -jar primegen.jar --sieve 100 200",
        "",
        "Options:",
        " --sieve   Use Sieve of Eratosthenes algorithm",
        " --help    Print this help text"
    };

    public static void printHelp() {
        for (String line : HELP_TEXT) {
            System.out.println(line);
        }
    }
}
