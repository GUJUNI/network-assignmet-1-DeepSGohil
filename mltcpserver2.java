import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class mltcpserver2 {
    public static void main(String[] args) {
        try {
            int serverPort = 12345;

            ServerSocket serverSocket = new ServerSocket(serverPort);
            System.out.println("Server is listening on port " + serverPort + "...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                BufferedReader inputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                OutputStream outputStream = clientSocket.getOutputStream();

                String clientMessage = inputReader.readLine();
                System.out.println("Received message from client: " + clientMessage);

                String responseMessage = "Hello, client!";
                outputStream.write(responseMessage.getBytes());
                outputStream.flush();

                clientSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
