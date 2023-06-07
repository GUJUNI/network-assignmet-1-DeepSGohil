import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class simptcpclient1 {
    public static void main(String[] args) {
        try {
            String serverAddress = "127.0.0.1";
            int serverPort = 12345;

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
