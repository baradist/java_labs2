package tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
    public static final int SERVER_PORT = 12345;
    public static final int BUFFER_SIZE = 256;

    private int port;

    public TcpServer() {
        this(SERVER_PORT);
    }

    public TcpServer(int port) {
        this.port = port;
    }

    public static void main(String... args) {
        TcpServer s = new TcpServer();
        s.run();
    }

    private void run() {
        try (ServerSocket serverSocket = new ServerSocket(port);
             Socket clientSocket = serverSocket.accept()) {
            byte[] buf = new byte[BUFFER_SIZE];
            InputStream in = clientSocket.getInputStream();
            OutputStream out = clientSocket.getOutputStream();
            while (true) {
                int length = in.read(buf);
                if (length < 0) {
                    break;
                }
                String msg = new String(buf, 0, length);
                System.out.println("Client sent a message: " + msg);

                String reply = msg.toUpperCase();
                out.write(reply.getBytes());
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        System.out.println("Connection closed");
    }
}
