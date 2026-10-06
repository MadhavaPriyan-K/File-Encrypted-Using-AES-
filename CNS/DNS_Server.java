import java.net.*;
import java.util.HashMap;

public class DNS_Server {

    public static void main(String[] args) throws Exception {

        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] receiveData = new byte[1024];
        byte[] sendData;

        HashMap<String, String> dns = new HashMap<>();

        dns.put("google.com", "142.250.183.14");
        dns.put("youtube.com", "142.250.190.206");
        dns.put("facebook.com", "157.240.22.35");
        dns.put("gmail.com", "142.250.183.101");
        dns.put("amazon.com", "205.251.242.103");

        System.out.println("DNS Server is Running...");

        while (true) {

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);

            String domain = new String(receivePacket.getData(), 0, receivePacket.getLength()).trim();

            String ip = dns.getOrDefault(domain, "Domain Not Found");

            sendData = ip.getBytes();

            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();

            DatagramPacket sendPacket =
                    new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);

            serverSocket.send(sendPacket);

            System.out.println("Request : " + domain + " -> " + ip);
        }
    }
}