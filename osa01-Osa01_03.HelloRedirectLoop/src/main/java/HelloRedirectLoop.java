
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class HelloRedirectLoop {

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8080);
        int count = 0;
        
        while (true) {
            Socket socket = server.accept();
            System.out.println("Total conections: " + count);
            count++;
            Scanner input = new Scanner(socket.getInputStream());
            if (input.equals("GET /quit")) {
                break;
            }
            
            PrintWriter printwriter = new PrintWriter(socket.getOutputStream());
            printwriter.println("HTTP/1.1 302 Found\nLocation: http://localhost:8080");
            printwriter.println();
            printwriter.flush();
            printwriter.close();
            input.close();
        }
        
    }
}
