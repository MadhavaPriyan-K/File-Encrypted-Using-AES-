import java.util.Scanner;

public class SHA1 {

    // Left rotate
    static int leftRotate(int value, int bits) {
        return (value << bits) | (value >>> (32 - bits));
    }

    // SHA-1 function
    static int f(int t, int b, int c, int d) {
        if (t <= 19)
            return (b & c) | (~b & d);
        else if (t <= 39)
            return b ^ c ^ d;
        else if (t <= 59)
            return (b & c) | (b & d) | (c & d);
        else
            return b ^ c ^ d;
    }

    // SHA-1 hash function
    static String sha1(String message) {

        byte[] input = message.getBytes();

        // Original length in bits
        long bitLength = (long) input.length * 8;

        // Calculate padded length
        int newLength = input.length + 1;
        while ((newLength % 64) != 56) {
            newLength++;
        }

        byte[] data = new byte[newLength + 8];

        // Copy original message
        System.arraycopy(input, 0, data, 0, input.length);

        // Append 1 bit followed by zeros
        data[input.length] = (byte) 0x80;

        // Append original length as 64-bit big-endian
        for (int i = 0; i < 8; i++) {
            data[data.length - 1 - i] =
                    (byte) (bitLength >>> (8 * i));
        }

        // Initial hash values
        int h0 = 0x67452301;
        int h1 = 0xEFCDAB89;
        int h2 = 0x98BADCFE;
        int h3 = 0x10325476;
        int h4 = 0xC3D2E1F0;

        // Process each 512-bit block
        for (int block = 0; block < data.length; block += 64) {

            int[] w = new int[80];

            // Break block into 16 words
            for (int i = 0; i < 16; i++) {
                int index = block + i * 4;

                w[i] = ((data[index] & 0xFF) << 24)
                     | ((data[index + 1] & 0xFF) << 16)
                     | ((data[index + 2] & 0xFF) << 8)
                     | (data[index + 3] & 0xFF);
            }

            // Extend 16 words to 80 words
            for (int i = 16; i < 80; i++) {
                w[i] = leftRotate(
                        w[i - 3] ^ w[i - 8] ^
                        w[i - 14] ^ w[i - 16], 1);
            }

            // Initialize working variables
            int a = h0;
            int b = h1;
            int c = h2;
            int d = h3;
            int e = h4;

            // 80 rounds
            for (int t = 0; t < 80; t++) {

                int k;

                if (t <= 19)
                    k = 0x5A827999;
                else if (t <= 39)
                    k = 0x6ED9EBA1;
                else if (t <= 59)
                    k = 0x8F1BBCDC;
                else
                    k = 0xCA62C1D6;

                int temp = leftRotate(a, 5)
                        + f(t, b, c, d)
                        + e
                        + w[t]
                        + k;

                e = d;
                d = c;
                c = leftRotate(b, 30);
                b = a;
                a = temp;
            }

            // Add result to current hash value
            h0 += a;
            h1 += b;
            h2 += c;
            h3 += d;
            h4 += e;
        }

        // Produce final 160-bit hash
        return String.format(
                "%08x%08x%08x%08x%08x",
                h0, h1, h2, h3, h4
        );
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the message: ");
        String message = sc.nextLine();

        String hash = sha1(message);

        System.out.println("\nSHA-1 Hash:");
        System.out.println(hash);

        sc.close();
    }
}