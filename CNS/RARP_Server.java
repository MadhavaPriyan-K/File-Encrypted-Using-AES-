import java.net.*;
import java.io.*;
import java.util.HashMap;

public class RARP_Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9877);

            HashMap<String, String> table = new HashMap<>();
            table.put("00:1A:2B:3C:4D:5E", "192.168.1.1");
            table.put("00:1A:2B:3C:4D:5F", "192.168.1.2");
            table.put("00:1A:2B:3C:4D:60", "192.168.1.3");
            table.put("00:1A:2B:3C:4D:61", "192.168.1.4");

            System.out.println("RARP Server Started...");
            System.out.println("Waiting for Client...");

            while (true) {
                Socket socket = serverSocket.accept();

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                String mac = in.readLine();
                System.out.println("Received : " + mac);

                String ip = table.getOrDefault(mac, "MAC Not Found");

                out.println(ip);
                System.out.println("Sent : " + ip);

                socket.close();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
