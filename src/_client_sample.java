import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;


public class _client_sample {
    public static void main(String[] args) throws Exception {
        String ip = "localhost";
        int port = 9900;
        String temp;
        Socket s = new Socket(ip,port);
        boolean cflag = true;
        Scanner sc1 = new Scanner(System.in);
        PrintStream os = new PrintStream(s.getOutputStream());
        while (cflag == true){
            System.out.print("Enter string: ");
            String cMessage = sc1.nextLine();
            // os.print(cMessaasdge);
            os.println(cMessage); 
        }


    }
}