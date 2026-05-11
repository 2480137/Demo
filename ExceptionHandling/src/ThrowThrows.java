import java.util.Scanner;

public class ThrowThrows {

    static void check(int age) throws Exception {
        if (age < 18) throw new Exception("Underage");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter age: ");
        int age = sc.nextInt();

        try {
            check(age);
            System.out.println("Eligible");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        sc.close();
    }
}