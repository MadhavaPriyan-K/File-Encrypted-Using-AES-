        import java.net.*;
import java.util.HashMap;
public class ARP_Server {
 public static void main(String[] args) {
 try {
 DatagramSocket serverSocket = new DatagramSocket(9876);
 HashMap<String, String> arpTable = new HashMap<>();
 arpTable.put("192.168.1.1", "00:1A:2B:3C:4D:5E");
 arpTable.put("192.168.1.2", "00:1A:2B:3C:4D:5F");
 arpTable.put("192.168.1.3", "00:1A:2B:3C:4D:60");
 arpTable.put("192.168.1.4", "00:1A:2B:3C:4D:61");
 byte[] receiveData = new byte[1024];
 System.out.println(" ARP SERVER STARTED");
 System.out.println("Listening on Port : 9876");
 System.out.println("Waiting for Client...");
 while (true) {
 DatagramPacket receivePacket = new DatagramPacket(receiveData, 
receiveData.length);
 serverSocket.receive(receivePacket);
 String ip = new String(receivePacket.getData(), 0, receivePacket.getLength()).trim();
 System.out.println("\nClient Connected!");
 System.out.println("Client Address : " + receivePacket.getAddress());
 System.out.println("Client Port : " + receivePacket.getPort());
 System.out.println("Requested IP : " + ip);
 String mac = arpTable.getOrDefault(ip, "IP Address Not Found");
 System.out.println("Sending MAC : " + mac);
 byte[] sendData = mac.getBytes();
 DatagramPacket sendPacket = new DatagramPacket(
 sendData,
 sendData.length,
 receivePacket.getAddress(),
 receivePacket.getPort());
 serverSocket.send(sendPacket);
 System.out.println("Response Sent Successfully.");
 }
 } catch (Exception e) {
 System.out.println(e);
 }
 }
}