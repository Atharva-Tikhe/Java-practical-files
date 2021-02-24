import java.io.*;

public class WriteToFile {

    String path;
    boolean append_to_file = false;

    public WriteToFile(String file_path) {
        path = file_path;

    }

    public WriteToFile(String file_path, boolean append_value) {
        // parameterized constructor with an option of appending to the file
        path = file_path;
        append_to_file = append_value;
    }

    public void write(String textLine) throws IOException{
        // This method writes the strings from any class to a file that is assigned to path variable
        FileWriter writer = new FileWriter(path, append_to_file);
        PrintWriter print_line = new PrintWriter(writer);
        print_line.printf("%s ", textLine);
        print_line.close();
    }


}

