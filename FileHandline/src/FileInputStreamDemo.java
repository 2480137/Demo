import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileInputStreamDemo {
    public static void main(String args[]){
        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\2480137\\IdeaProjects\\FileHandline\\src\\file.txt");
            int ch;
            while((ch = fis.read()) != -1){
                System.out.print(" " + (char) ch );
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
