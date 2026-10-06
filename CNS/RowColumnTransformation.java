import java.util.*;

public class RowColumnTransformation {

    // Encryption
    static String encrypt(String text, String key) {
        int cols = key.length();
        int rows = (int) Math.ceil((double) text.length() / cols);

        char[][] matrix = new char[rows][cols];
        int k = 0;

        // Write plaintext in rows
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (k < text.length())
                    matrix[i][j] = text.charAt(k++);
                else
                    matrix[i][j] = 'X';
            }
        }

        // Find alphabetical order of key
        Integer[] order = new Integer[cols];
        for (int i = 0; i < cols; i++)
            order[i] = i;

        Arrays.sort(order, (a, b) ->
            Character.compare(key.charAt(a), key.charAt(b)));

        // Read columns in key order
        StringBuilder cipher = new StringBuilder();

        for (int col : order) {
            for (int row = 0; row < rows; row++) {
                cipher.append(matrix[row][col]);
            }
        }

        return cipher.toString();
    }

    // Decryption
    static String decrypt(String cipher, String key) {
        int cols = key.length();
        int rows = cipher.length() / cols;

        char[][] matrix = new char[rows][cols];
        Integer[] order = new Integer[cols];

        for (int i = 0; i < cols; i++)
            order[i] = i;

        Arrays.sort(order, (a, b) ->
            Character.compare(key.charAt(a), key.charAt(b)));

        int k = 0;

        // Fill columns according to key order
        for (int col : order) {
            for (int row = 0; row < rows; row++) {
                matrix[row][col] = cipher.charAt(k++);
            }
        }

        // Read rows
        StringBuilder plain = new StringBuilder();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                plain.append(matrix[i][j]);
            }
        }

        return plain.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter plaintext: ");
        String text = sc.nextLine().replaceAll("\\s+", "").toUpperCase();

        System.out.print("Enter key: ");
        String key = sc.nextLine().toUpperCase();

        String cipher = encrypt(text, key);

        System.out.println("Encrypted Text : " + cipher);

        String decrypted = decrypt(cipher, key);

        System.out.println("Decrypted Text : " + decrypted);

        sc.close();
    }
}
