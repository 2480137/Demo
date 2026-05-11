import java.util.*;

public class PriorityQueueDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        System.out.println("Enter number of elements:");
        int n = sc.nextInt();

        for(int i = 0; i < n; i++) {
            System.out.println("Enter element:");
            pq.add(sc.nextInt());
        }

        System.out.println("PriorityQueue: " + pq);

        System.out.println("Enter element to remove:");
        int remove = sc.nextInt();
        pq.remove(remove);
        System.out.println("After removal: " + pq);

        System.out.println("Head element (peek): " + pq.peek());

        System.out.println("Polling elements:");
        while(!pq.isEmpty()) {
            System.out.println(pq.poll());
        }
    }
}