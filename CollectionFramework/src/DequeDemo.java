import java.util.*;

public class DequeDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Deque<String> deque = new ArrayDeque<>();

        System.out.println("Enter number of elements:");
        int n = sc.nextInt();
        sc.nextLine();

        for(int i = 0; i < n; i++) {
            System.out.println("Enter element:");
            deque.addLast(sc.nextLine());
        }

        System.out.println("Deque: " + deque);

        System.out.println("Adding element at front:");
        deque.addFirst(sc.nextLine());
        System.out.println("After addFirst: " + deque);

        System.out.println("Adding element at last:");
        deque.addLast(sc.nextLine());
        System.out.println("After addLast: " + deque);

        System.out.println("Removing first: " + deque.removeFirst());
        System.out.println("Removing last: " + deque.removeLast());
        System.out.println("Deque now: " + deque);

        System.out.println("Front element: " + deque.peekFirst());
        System.out.println("Last element: " + deque.peekLast());
    }
}