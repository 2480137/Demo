import java.util.Scanner;

public class CustomExceptionDemo {

    // Custom CHECKED exception (must be declared or caught)
    static class InvalidAgeException extends Exception {
        public InvalidAgeException(String message) { super(message); }
    }

    // Custom UNCHECKED exception (RuntimeException)
    static class NegativeAmountException extends RuntimeException {
        public NegativeAmountException(String message) { super(message); }
    }

    static void verifyAge(int age) throws InvalidAgeException {
        if (age < 18) throw new InvalidAgeException("Age must be 18 or above.");
    }

    static void deposit(double amount) {
        if (amount < 0) throw new NegativeAmountException("Amount cannot be negative.");
        System.out.println("Deposited: " + amount);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter age: ");
            int age = Integer.parseInt(sc.nextLine());
            verifyAge(age);
            System.out.println("Age verification passed.");

            System.out.print("Enter amount to deposit: ");
            double amt = Double.parseDouble(sc.nextLine());
            deposit(amt);
            System.out.println("Deposit successful.");
        } catch (InvalidAgeException e) {
            System.out.println("Checked exception: " + e.getMessage());
        } catch (NegativeAmountException e) {
            System.out.println("Unchecked exception: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number input.");
        } finally {
            sc.close();
        }
    }
}