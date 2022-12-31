import java.net.*;
import java.io.*;
import java.util.Scanner;
public class UDPServer {

    public static void main(String args[]) {
        DatagramSocket aSocket = null;
        Scanner input = new Scanner(System.in);
        try {
            System.out.println("Enter a Port number:");
            int serverPort = input.nextInt();
            input.nextLine();
            aSocket = new DatagramSocket(serverPort);
            byte[] buffer = null;
            while (true) {
                buffer = new byte[1000];
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);
                String message = new String(request.getData());
                String result = message.toUpperCase();
                byte [] response = result.getBytes();
                DatagramPacket reply = new DatagramPacket(response,
                        response.length, request.getAddress(), request.getPort());
                aSocket.send(reply);
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