import java.util.Scanner;

interface Multiple {
    public int mul(int a, int b);

}
public class LambdaDemo {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        System.out.println("Enter Number 1");
        int a1 = s.nextInt();

        System.out.println("Enter Number 2");
        int b1 = s.nextInt();

        // Lambda implementing the Multiple interface
        Multiple obj = (a, b) -> (a * b);
        System.out.println("Result is - " + obj.mul(a1, b1));
    }
}