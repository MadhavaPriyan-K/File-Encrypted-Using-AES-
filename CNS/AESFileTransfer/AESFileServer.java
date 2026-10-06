import java.io.*;
import java.net.*;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESFileServer {

    private static final int PORT = 5000;

    // 16-byte AES key
    private static final String SECRET_KEY = "1234567890123456";

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            System.out.println("Server started...");
            System.out.println("Waiting for client...");

            Socket socket = serverSocket.accept();

            System.out.println("Client connected!");

            DataInputStream dis =
                    new DataInputStream(socket.getInputStream());

            // Receive file name
            String fileName = dis.readUTF();

            // Receive encrypted file size
            long fileSize = dis.readLong();

            byte[] encryptedData = new byte[(int) fileSize];

            dis.readFully(encryptedData);

            System.out.println("Encrypted file received: " + fileName);

            // Decrypt file
            byte[] decryptedData = decrypt(encryptedData);

            // Save decrypted file
            FileOutputStream fos =
                    new FileOutputStream("received_" + fileName);

            fos.write(decryptedData);
            fos.close();

            System.out.println("File decrypted successfully!");
            System.out.println("Saved as: received_" + fileName);

            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // AES Decryption
    public static byte[] decrypt(byte[] data) throws Exception {

        SecretKeySpec key =
                new SecretKeySpec(SECRET_KEY.getBytes(), "AES");

        Cipher cipher = Cipher.getInstance("AES");

        cipher.init(Cipher.DECRYPT_MODE, key);

        return cipher.doFinal(data);
    }
}