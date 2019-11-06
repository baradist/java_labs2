package net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

import static tcp.TcpServer.BUFFER_SIZE;
import static tcp.TcpServer.SERVER_PORT;

public class TcpClient {
    static final String SERVER_HOST = "localhost";

    private String host;
    private int port;

    public TcpClient() {
        this(SERVER_HOST, SERVER_PORT);
    }

    public TcpClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static void main(String... args) {
        TcpClient s = new TcpClient();
        s.run();
    }

    private void run() {
        try (Socket clientSocket = new Socket(host, port)) {
            do {
                String message = readMessage();
                if (message.toLowerCase().equals("exit")) {
                    System.out.println("Connection closed");
                    break;
                }
                OutputStream out = clientSocket.getOutputStream();
                out.write(message.getBytes());

                InputStream in = clientSocket.getInputStream();
                byte[] buf = new byte[BUFFER_SIZE];
                int length = in.read(buf);
                String msg = new String(buf, 0, length);
                System.out.println("Server respond: " + msg);
            } while (true);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private String readMessage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input your message>");
        return scanner.nextLine();
    }
}
