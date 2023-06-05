# Prime Number Generator

A simple Java application which prints a list of prime numbers.

# Build

* `mvn clean`
* `mvn compile`
* `mvn package`

# Run

```
java -jar ./target/primegen-1.0.0.jar [OPTION] LOWER UPPER
Print prime numbers from LOWER to UPPER (inclusive)

Examples:
  java -jar ./target/primegen-1.0.0.jar 0 100
  java -jar ./target/primegen-1.0.0.jar --sieve 100 200
  java -jar ./target/primegen-1.0.0.jar --noprint --time --sieve2 2000000000 2100000000

Options:
  --sieve       Use Sieve of Eratosthenes algorithm
  --sieve2      Use segmented Sieve of Eratosthenes optimized for large LOWER and UPPER bounds
  --count       Print how many prime numbers were generated
  --time        Print time it took to generate primes at very end
  --noprint     Do not print prime numbers
  --help        Print this help text
```
