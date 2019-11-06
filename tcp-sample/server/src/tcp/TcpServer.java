package tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static java.time.format.DateTimeFormatter.ISO_TIME;

public class TcpServer {
    public static final int SERVER_PORT = 12345;
    public static final int BUFFER_SIZE = 256;

    private int port;
    //    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(ISO_LOCAL_DATE);
    DateTimeFormatter dateFormatter = ISO_LOCAL_DATE;
    DateTimeFormatter timeFormatter = ISO_TIME;

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
                System.out.println("Client requested: " + msg);
                if ("date".equalsIgnoreCase(msg)) {
                    out.write(LocalDate.now().format(dateFormatter).getBytes());
                } else if ("time".equalsIgnoreCase(msg)) {
                    out.write(LocalDateTime.now().format(timeFormatter).getBytes());
                }
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        System.out.println("Connection closed");
    }
}
