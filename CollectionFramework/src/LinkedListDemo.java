import java.util.*;

public class LinkedListDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedList<String> list = new LinkedList<>();

        System.out.println("Enter number of elements:");
        int n = sc.nextInt();
        sc.nextLine();

        for(int i = 0; i < n; i++) {
            System.out.println("Enter element:");
            list.add(sc.nextLine());
        }

        System.out.println("LinkedList: " + list);

        System.out.println("Enter element to remove:");
        String remove = sc.nextLine();
        list.remove(remove);
        System.out.println("After removal: " + list);

        System.out.println("Enter element to search:");
        String search = sc.nextLine();
        if(list.contains(search)) {
            System.out.println("Found");
        } else {
            System.out.println("Not Found");
        }

        list.addFirst("FirstItem");
        list.addLast("LastItem");
        System.out.println("After adding first and last: " + list);
    }
}