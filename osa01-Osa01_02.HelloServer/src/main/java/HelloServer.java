

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class HelloServer {

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8080);
        
        while (true) {
            Socket socket = server.accept();
            Scanner input = new Scanner(socket.getInputStream());
            if (input.equals("GET /quit")) {
                break;
            }
            PrintWriter printwriter = new PrintWriter(socket.getOutputStream());
            printwriter.println("HTTP/1.1 200 OK");
            printwriter.println();
            try {
                Files.lines(Paths.get("index.html"))
                        .forEach(s -> printwriter.println(s));
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
            printwriter.println();
            printwriter.flush();
        }
        
        
    }
}
