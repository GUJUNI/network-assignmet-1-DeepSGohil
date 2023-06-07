import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class simpudpserver2 {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(12345);
            System.out.println("Server is listening on port 12345...");

            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);

                socket.receive(receivePacket);

                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received message: " + message);

                String modifiedMessage = message.toUpperCase();

                byte[] responseData = modifiedMessage.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(responseData, responseData.length,
                        receivePacket.getAddress(), receivePacket.getPort());
                socket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
