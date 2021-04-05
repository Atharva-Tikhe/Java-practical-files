import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class client_view implements ActionListener {
    //global socket settings
    String IP_ADD = "localhost";
    int PORT = 9900;
    String message;
    Socket s;
    PrintStream pr;


    // GUI components
    JFrame frame = new JFrame("Client View");
    JTextArea messageArea = new JTextArea();
    JTextField messageText = new JTextField();
    JButton sendButton = new JButton("Send");


    client_view() throws IOException {
        frame.setSize(620,500);
        frame.setLayout(null);
        frame.setBackground(Color.lightGray);
        messageArea.setBounds(6,6,580,300);
        messageText.setBounds(6,320,500,25);
        sendButton.setBounds(510,320,70, 25);
        sendButton.addActionListener(this);
        frame.add(sendButton);
        frame.add(messageText);
        frame.add(messageArea);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        s = new Socket(IP_ADD,PORT);
        pr = new PrintStream(s.getOutputStream());
        pr.flush();
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        do {
            messageArea.append(in.readLine() + "\n");

        } while (!in.readLine().contains("stop"));
    }

    public void sendSocket(String message) {
        pr.println("[CLIENT] : " + message + "\n");
        messageArea.append("ME: " + message + "\n");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        message = messageText.getText();
        sendSocket(message);
        messageText.setText(null);
    }

    public static void main(String[] args) throws IOException {
        new client_view();

    }






}
