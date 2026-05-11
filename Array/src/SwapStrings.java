import java.util.Scanner;

public class SwapStrings {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read inputs
        System.out.print("Enter first string (A): ");
        String a = sc.nextLine();
        System.out.print("Enter second string (B): ");
        String b = sc.nextLine();

        // Swap without third variable
        a = a + b;                          // A = "AoriginalBoriginal"
        b = a.substring(0, a.length() - b.length()); // B = "Aoriginal"
        a = a.substring(b.length());        // A = "Boriginal"

        // Output
        System.out.println("After swap:");
        System.out.println("A = " + a);
        System.out.println("B = " + b);

        sc.close();
    }
}