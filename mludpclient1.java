import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class mludpclient1 {
    public static void main(String[] args) {
        try {
            InetAddress serverAddress = InetAddress.getByName("127.0.0.1");
            int serverPort = 12345;

            for (int i = 0; i < 5; i++) {
                Thread clientThread = new Thread(new ClientThread(serverAddress, serverPort));
                clientThread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class ClientThread implements Runnable {
        private InetAddress serverAddress;
        private int serverPort;

        public ClientThread(InetAddress serverAddress, int serverPort) {
            this.serverAddress = serverAddress;
            this.serverPort = serverPort;
        }

        @Override
        public void run() {
            try {
                DatagramSocket socket = new DatagramSocket();

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
}
