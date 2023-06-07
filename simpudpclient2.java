import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class simpudpclient2
 {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();

            InetAddress serverAddress = InetAddress.getByName("127.0.0.1");
            int serverPort = 12345;

            String message = "Hello, server!";
            byte[] sendData = message.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);

            socket.send(sendPacket);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);

            String serverResponse = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Server response: " + serverResponse);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
