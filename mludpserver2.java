import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class mludpserver2 {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(12345);
            System.out.println("Server is listening on port 12345...");

            for (int i = 0; i < 5; i++) {
                Thread serverThread = new Thread(new ServerThread(socket));
                serverThread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class ServerThread implements Runnable {
        private DatagramSocket socket;

        public ServerThread(DatagramSocket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                byte[] buffer = new byte[1024];

                while (true) {
                    DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);

                    socket.receive(receivePacket);

                    String clientAddress = receivePacket.getAddress().getHostAddress();
                    int clientPort = receivePacket.getPort();

                    String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                    System.out.println("Received message from " + clientAddress + ":" + clientPort + ": " + message);

                    String responseMessage = "Hello, client!";
                    byte[] responseData = responseMessage.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(responseData, responseData.length,
                            receivePacket.getAddress(), receivePacket.getPort());
                    socket.send(sendPacket);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
