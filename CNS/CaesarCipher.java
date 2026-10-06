import java.util.Scanner;

public class CaesarCipher {

    // Encryption method
    static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();

        shift = shift % 26;

        for (char ch : text.toCharArray()) {

            if (Character.isUpperCase(ch)) {
                ch = (char) ((ch - 'A' + shift) % 26 + 'A');
            } 
            else if (Character.isLowerCase(ch)) {
                ch = (char) ((ch - 'a' + shift) % 26 + 'a');
            }

            result.append(ch);
        }

        return result.toString();
    }

    // Decryption method
    static String decrypt(String text, int shift) {
        return encrypt(text, 26 - (shift % 26));
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter message: ");
        String message = sc.nextLine();

        System.out.print("Enter the shift: ");
        int shift = sc.nextInt();

        String encrypted = encrypt(message, shift);
        String decrypted = decrypt(encrypted, shift);

        System.out.println("Original  : " + message);
        System.out.println("Encrypted : " + encrypted);
        System.out.println("Decrypted : " + decrypted);

        sc.close();
    }
}