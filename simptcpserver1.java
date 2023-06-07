import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class simptcpserver1 {
    public static void main(String[] args) {
        try {
            int serverPort = 12345;

            ServerSocket serverSocket = new ServerSocket(serverPort);
            System.out.println("Server is listening on port " + serverPort + "...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress());

            BufferedReader inputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            OutputStream outputStream = clientSocket.getOutputStream();

            String clientMessage = inputReader.readLine();
            System.out.println("Received message from client: " + clientMessage);

            String responseMessage = "Hello, client!";
            outputStream.write(responseMessage.getBytes());
            outputStream.flush();

            clientSocket.close();

            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
