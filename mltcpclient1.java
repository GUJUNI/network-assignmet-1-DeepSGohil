import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class mltcpclient1 {
    public static void main(String[] args) {
        try {
            String serverAddress = "127.0.0.1";
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
        private String serverAddress;
        private int serverPort;

        public ClientThread(String serverAddress, int serverPort) {
            this.serverAddress = serverAddress;
            this.serverPort = serverPort;
        }

        @Override
        public void run() {
            try {
                Socket socket = new Socket(serverAddress, serverPort);

                BufferedReader inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                OutputStream outputStream = socket.getOutputStream();

                String message = "Hello, server!";
                outputStream.write(message.getBytes());
                outputStream.flush();

                String serverResponse = inputReader.readLine();
                System.out.println("Server response: " + serverResponse);

                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
