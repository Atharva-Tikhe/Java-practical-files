import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class server_view implements ActionListener {
    // global Socket settings
    String serverMessage;
    ServerSocket ss = new ServerSocket(9900);
    Socket s;
    PrintStream pr;

    //GUI components
    JFrame serverFrame = new JFrame("Server View");
    JTextArea serverMessageArea = new JTextArea();
    JTextField serverMessageText = new JTextField();
    JButton serverSendButton = new JButton("Send");

    server_view() throws IOException {
        serverFrame.setSize(620,500);
        serverFrame.setLayout(null);
        serverFrame.setBackground(Color.lightGray);
        serverMessageArea.setBounds(6,6,580,300);
        serverMessageText.setBounds(6,320,500,25);
        serverSendButton.setBounds(510,320,70, 25);

        serverSendButton.addActionListener(this);

        serverFrame.add(serverSendButton);
        serverFrame.add(serverMessageText);
        serverFrame.add(serverMessageArea);
        serverFrame.setVisible(true);
        serverFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Socket programming

        s = ss.accept();
        pr = new PrintStream(s.getOutputStream());
        pr.flush();
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        do {
            serverMessageArea.append(in.readLine() + "\n");

        } while (!in.readLine().contains("stop"));

    }

    public void sendToClient(String message){
        pr.println("[SERVER] : " + message + "\n");
        serverMessageArea.append("ME: " + message + "\n");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        serverMessage = serverMessageText.getText(); // not needed right now
        sendToClient(serverMessage);
        serverMessageText.setText(null);
    }

    public static void main(String[] args) throws IOException {
        new server_view();

    }
}
