import java.util.*;

public class StackDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<Integer> stack = new Stack<>();

        System.out.println("Enter number of elements:");
        int n = sc.nextInt();

        for(int i = 0; i < n; i++) {
            System.out.println("Enter element:");
            stack.push(sc.nextInt());
        }

        System.out.println("Stack: " + stack);

        System.out.println("Enter element to search:");
        int search = sc.nextInt();
        if(stack.search(search) != -1) {
            System.out.println("Found");
        } else {
            System.out.println("Not Found");
        }

        System.out.println("Popping elements:");
        while(!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}