import java.io.*;
import java.net.*;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESFileClient {

    private static final String SERVER_IP = "localhost";
    private static final int PORT = 5000;

    // Same 16-byte AES key
    private static final String SECRET_KEY = "1234567890123456";

    public static void main(String[] args) {

        try {

            // File to send
            File file = new File("sample.txt");

            // Read file
            FileInputStream fis =
                    new FileInputStream(file);

            byte[] fileData = new byte[(int) file.length()];

            fis.read(fileData);
            fis.close();

            // Encrypt file
            byte[] encryptedData = encrypt(fileData);

            System.out.println("File encrypted successfully!");

            // Connect to server
            Socket socket =
                    new Socket(SERVER_IP, PORT);

            System.out.println("Connected to server!");

            DataOutputStream dos =
                    new DataOutputStream(socket.getOutputStream());

            // Send file name
            dos.writeUTF(file.getName());

            // Send encrypted file size
            dos.writeLong(encryptedData.length);

            // Send encrypted file
            dos.write(encryptedData);

            dos.flush();

            System.out.println("Encrypted file sent successfully!");

            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // AES Encryption
    public static byte[] encrypt(byte[] data) throws Exception {

        SecretKeySpec key =
                new SecretKeySpec(SECRET_KEY.getBytes(), "AES");

        Cipher cipher = Cipher.getInstance("AES");

        cipher.init(Cipher.ENCRYPT_MODE, key);

        return cipher.doFinal(data);
    }
}