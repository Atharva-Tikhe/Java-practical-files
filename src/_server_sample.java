import java.net.ServerSocket;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class _server_sample {
    _server_sample() throws Exception{
        System.out.println("Server started...");
        ServerSocket ss = new ServerSocket(9900);
        System.out.println("Server standby...");
        Socket s = ss.accept();
        Scanner sc = new Scanner(s.getInputStream());
        // String message = sc.next();
        boolean flag = true;
        while (flag){
            try{
                String message = sc.nextLine();
                System.out.println(message);
                if (message.equals("^C")){
                    break;
                }
            }catch (NoSuchElementException e ) {
                System.out.println("line ended");
                flag = false;
            }
        }
    }
    
    
    public static void main(String[] args) throws Exception {
        _server_sample s1 = new _server_sample();
    }



}
