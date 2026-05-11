import java.util.Scanner;

public class RemoveNumbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = sc.nextLine();

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            // Keep only alphabets (A-Z or a-z)
            if (Character.isLetter(ch)) {
                result.append(ch);
            }
        }

        System.out.println("Alphabets only: " + result.toString());

        sc.close();
    }
}