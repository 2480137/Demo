import java.io.File;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        File f = new File("file.txt");
        System.out.println("File Exist : " + f.exists());
        System.out.println("Name : " + f.getName());
        System.out.println("Path : " + f.getAbsolutePath());
    }
}