import java.math.BigInteger;
import java.util.Scanner;

public class DiffieHellman {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter prime number (q): ");
        BigInteger q = new BigInteger(scanner.next());

        System.out.print("Enter primitive root of q (alpha): ");
        BigInteger alpha = new BigInteger(scanner.next());

        System.out.print("Enter private key for Alice (xa): ");
        BigInteger xa = new BigInteger(scanner.next());

        System.out.print("Enter private key for Bob (xb): ");
        BigInteger xb = new BigInteger(scanner.next());

        // Generate public keys
        BigInteger Ya = alpha.modPow(xa, q);
        BigInteger Yb = alpha.modPow(xb, q);

        System.out.println("\n--- Public Key Generation ---");
        System.out.println("Alice's Public Key (Ya): " + Ya);
        System.out.println("Bob's Public Key (Yb): " + Yb);

        // Generate shared secret keys
        BigInteger ka = Yb.modPow(xa, q);
        BigInteger kb = Ya.modPow(xb, q);

        System.out.println("\n--- Secret Key Exchange ---");
        System.out.println("Secret Key computed by Alice (Ka): " + ka);
        System.out.println("Secret Key computed by Bob (Kb): " + kb);

        // Check whether both keys are equal
        if (ka.equals(kb)) {
            System.out.println("\nSUCCESS: Both secret keys match!");
            System.out.println("Shared Secret Key = " + ka);
        } else {
            System.out.println("\nERROR: Keys do not match.");
        }

        scanner.close();
    }
}