import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

public class RSAAlgorithm {

    private BigInteger n;
    private BigInteger e;
    private BigInteger d;

    public RSAAlgorithm() {
        SecureRandom random = new SecureRandom();

        // Generate two prime numbers
        BigInteger p = BigInteger.probablePrime(512, random);
        BigInteger q = BigInteger.probablePrime(512, random);

        // n = p * q
        n = p.multiply(q);

        // phi(n) = (p-1)(q-1)
        BigInteger phi = p.subtract(BigInteger.ONE)
                          .multiply(q.subtract(BigInteger.ONE));

        // Choose public exponent e
        e = BigInteger.valueOf(65537);

        // Calculate private key d
        d = e.modInverse(phi);
    }

    // Encryption
    public BigInteger encrypt(String message) {
        BigInteger msg = new BigInteger(message.getBytes());
        return msg.modPow(e, n);
    }

    // Decryption
    public String decrypt(BigInteger encrypted) {
        BigInteger decrypted = encrypted.modPow(d, n);
        return new String(decrypted.toByteArray());
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        RSAAlgorithm rsa = new RSAAlgorithm();

        System.out.print("Enter the message: ");
        String message = scanner.nextLine();

        BigInteger encrypted = rsa.encrypt(message);

        System.out.println("\nPublic Key (e): " + rsa.e);
        System.out.println("Public Key (n): " + rsa.n);

        System.out.println("\nEncrypted Message:");
        System.out.println(encrypted);

        String decrypted = rsa.decrypt(encrypted);

        System.out.println("\nDecrypted Message:");
        System.out.println(decrypted);

        scanner.close();
    }
}