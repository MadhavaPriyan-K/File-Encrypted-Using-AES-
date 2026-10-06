import java.net.*;
import java.util.Scanner;

public class DNS_Client {

    public static void main(String[] args) throws Exception {

        DatagramSocket clientSocket = new DatagramSocket();

        InetAddress IPAddress = InetAddress.getByName("localhost");

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Domain Name : ");
        String domain = sc.nextLine();

        byte[] sendData = domain.getBytes();
        byte[] receiveData = new byte[1024];

        DatagramPacket sendPacket =
                new DatagramPacket(sendData, sendData.length, IPAddress, 9876);

        clientSocket.send(sendPacket);

        DatagramPacket receivePacket =
                new DatagramPacket(receiveData, receiveData.length);

        clientSocket.receive(receivePacket);

        String ip =
                new String(receivePacket.getData(), 0, receivePacket.getLength());

        System.out.println("IP Address : " + ip);

        clientSocket.close();
    }
}