import java.net.*;
import java.util.Scanner;
public class ARP_Client {
 public static void main(String[] args) {
 try {
 DatagramSocket clientSocket = new DatagramSocket();
 InetAddress serverAddress = InetAddress.getByName("localhost");
 Scanner sc = new Scanner(System.in);
 System.out.println(" ARP CLIENT");
 System.out.print("Enter IP Address : ");
 String ip = sc.nextLine();
 byte[] sendData = ip.getBytes();
 byte[] receiveData = new byte[1024];
 DatagramPacket sendPacket = new DatagramPacket(
 sendData,
 sendData.length,
 serverAddress, 9876);
 clientSocket.send(sendPacket);
 System.out.println("Request Sent to Server...");
 DatagramPacket receivePacket = new DatagramPacket(receiveData,
receiveData.length);
 clientSocket.receive(receivePacket);
 String mac = new String(receivePacket.getData(), 0, receivePacket.getLength());
 System.out.println("MAC Address : " + mac);
 clientSocket.close();
 sc.close();
 } catch (Exception e) {
 System.out.println(e);
 }
 }
}
