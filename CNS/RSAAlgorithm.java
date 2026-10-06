import java.util.Scanner;

public class RSAAlgorithm {

    // Find GCD
    static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Find modular inverse of e
    static long modInverse(long e, long phi) {
        for (long d = 1; d < phi; d++) {
            if ((e * d) % phi == 1) {
                return d;
            }
        }
        return -1;
    }

    // Calculate (base^exponent) % modulus
    static long powerMod(long base, long exponent, long modulus) {
        long result = 1;

        while (exponent > 0) {
            result = (result * base) % modulus;
            exponent--;
        }

        return result;
    }

    // Check whether a number is prime
    static boolean isPrime(long num) {
        if (num < 2) {
            return false;
        }

        for (long i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Step 1: Select two prime numbers
        System.out.print("Enter prime number p: ");
        long p = sc.nextLong();

        System.out.print("Enter prime number q: ");
        long q = sc.nextLong();

        // Check whether p and q are prime
        if (!isPrime(p) || !isPrime(q)) {
            System.out.println("Error: p and q must be prime numbers.");
            sc.close();
            return;
        }

        if (p == q) {
            System.out.println("Error: p and q must be different.");
            sc.close();
            return;
        }

        // Step 2: Compute n = p * q
        long n = p * q;

        // Step 3: Compute phi(n)
        long phi = (p - 1) * (q - 1);

        System.out.println("\nn = " + n);
        System.out.println("phi(n) = " + phi);

        // Step 4: Select encryption key e
        long e;

        while (true) {

            System.out.print("Enter encryption key e: ");
            e = sc.nextLong();

            if (e <= 1 || e >= phi) {
                System.out.println(
                    "e must be greater than 1 and less than phi(n)."
                );
                continue;
            }

            if (gcd(e, phi) != 1) {
                System.out.println(
                    "e must be relatively prime to phi(n). Try again."
                );
                continue;
            }

            // Calculate d
            long testD = modInverse(e, phi);

            // Make sure public and private exponents are different
            if (testD == e) {
                System.out.println(
                    "This e produces the same public and private exponent."
                );
                System.out.println("Please choose another e.");
                continue;
            }

            break;
        }

        // Step 5: Calculate decryption key d
        long d = modInverse(e, phi);

        // Step 6: Display keys
        System.out.println("\n========== RSA KEYS ==========");
        System.out.println("Public Key  = (" + e + ", " + n + ")");
        System.out.println("Private Key = (" + d + ", " + n + ")");

        // Step 7: Encryption
        System.out.print(
            "\nEnter plaintext M (number less than n): "
        );

        long m = sc.nextLong();

        if (m < 0 || m >= n) {
            System.out.println(
                "Plaintext must be between 0 and n-1."
            );
            sc.close();
            return;
        }

        long c = powerMod(m, e, n);

        System.out.println(
            "Encrypted Ciphertext C = " + c
        );

        // Step 8: Decryption
        long decrypted = powerMod(c, d, n);

        System.out.println(
            "Decrypted Plaintext M = " + decrypted
        );

        sc.close();
    }
}