import java.net.*;
import java.util.Scanner;
import java.io.*;
public class UDPClient {
    public static void main(String args[]) {
        DatagramSocket aSocket = null;
        Scanner input = new Scanner(System.in);
        try {
            aSocket = new DatagramSocket();
            byte[] buffer = new byte[1000];
            while(true) {
                System.out.println("Enter Ip: ");
                String ipAddress = input.nextLine();
                System.out.println("Enter a Port number:");
                int portNum = input.nextInt();
                input.nextLine();
                System.out.println("Enter the message you want to send to the host:");
                String message = input.nextLine();
                byte [] m = message.getBytes();
                // Turning the messages in an array of bytes.
                InetAddress aHost = InetAddress.getByName(ipAddress);
                DatagramPacket request = new DatagramPacket(m, m.length, aHost, portNum);
                aSocket.send(request);
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(reply);
                System.out.println("Reply: " + new String(reply.getData()));
            }
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
            // Error checking for the socket.
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
            // Error checking for input and outputs.
        } finally {
            if (aSocket != null) aSocket.close();
        }
        input.close();
    }
}