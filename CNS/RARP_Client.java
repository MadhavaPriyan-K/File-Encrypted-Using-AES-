import java.net.*;
import java.io.*;
import java.util.Scanner;

public class RARP_Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 9877);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            Scanner sc = new Scanner(System.in);

            System.out.print("Enter MAC Address : ");
            String mac = sc.nextLine();

            out.println(mac);

            String ip = in.readLine();

            System.out.println("IP Address : " + ip);

            socket.close();
            sc.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
