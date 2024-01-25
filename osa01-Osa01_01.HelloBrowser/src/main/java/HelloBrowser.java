
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class HelloBrowser {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("================\nTHE INTERNETS!\n================\nWhere to?");
        String address = scanner.nextLine();
        Socket socket = new Socket(address, 80);
        
        PrintWriter printwriter = new PrintWriter(socket.getOutputStream());
        printwriter.println("GET / HTTP/1.1");
        printwriter.println("host: " + address);
        printwriter.println();
        printwriter.flush();
        
        System.out.println("==========\nRESPONSE\n==========");
        Scanner input = new Scanner(socket.getInputStream());
        while (input.hasNextLine()) {
            System.out.println(input.nextLine());
        }
    }
}
