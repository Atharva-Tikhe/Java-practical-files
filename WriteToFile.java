import java.io.*;
import java.nio.CharBuffer;
import java.util.Scanner;

public class WriteToFile {

    String path;
    boolean append_to_file = false;
    int stringBufferLength;
    StringBuilder stringBuffRead = new StringBuilder();

    public WriteToFile(String file_path) {
        path = file_path;

    }

    public WriteToFile(String file_path, boolean append_value) {
        path = file_path;
        append_to_file = append_value;
    }

    public WriteToFile() throws IOException {

    }

    public void write(String textLine) throws IOException{
        FileWriter writer = new FileWriter(path, append_to_file);
        PrintWriter print_line = new PrintWriter(writer);

//        print_line.printf("Name: %s \n ", textLine);
//        print_line.printf("Roll. No.: %s \n ", textLine);
//        print_line.printf("Address: %s \n ", textLine);
//        print_line.printf("Gender: %s \n ", textLine);
        print_line.printf("%s ", textLine);
        print_line.close();

    }

    public int readFromFile(String fileName) throws IOException {
        File file = new File(fileName);
        FileReader reader = new FileReader(file);
        BufferedReader buffRead = new BufferedReader(reader);
        String readLine;
        while((readLine=buffRead.readLine())!=null){
            stringBuffRead.append(readLine);
            stringBuffRead.append("\n");

        }
        reader.close();
        return stringBuffRead.length();
    }
}

//
//
//    public void readFromFile() throws FileNotFoundException {
//        File file = new File("./saved_details.txt");
//        FileInputStream stream = new FileInputStream(file);
//        Scanner scanLine = new Scanner(stream);
//        while(scanLine.hasNextLine()){
//            if(scanLine.toString().equals(infoArea.getText())){
//                infoArea.setVisible(true);
//                infoArea.append("\n");
//                infoArea.append(scanLine.nextLine());
//                System.out.println(scanLine.nextLine());
//            }else{
//                break;
//            }
//        }
//    }