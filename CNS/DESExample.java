import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Scanner;

public class DESExample {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        SecretKey secretKey = keyGenerator.generateKey();

        
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

        
        System.out.print("Enter the text: ");
        String plainText = sc.nextLine();

        
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());

        String encryptedText = Base64.getEncoder()
                .encodeToString(encryptedBytes);

        System.out.println("Encrypted Text: " + encryptedText);

        
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        String decryptedText = new String(decryptedBytes);

        System.out.println("Decrypted Text: " + decryptedText);

        sc.close();
    }
}
