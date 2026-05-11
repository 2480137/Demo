import java.util.*;

public class TreeSetDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TreeSet<String> set = new TreeSet<>();

        System.out.println("Enter number of elements:");
        int n = sc.nextInt();
        sc.nextLine();

        for(int i = 0; i < n; i++) {
            System.out.println("Enter element:");
            set.add(sc.nextLine());
        }

        System.out.println("TreeSet: " + set);

        System.out.println("Enter element to remove:");
        String remove = sc.nextLine();
        set.remove(remove);
        System.out.println("After removal: " + set);

        System.out.println("Enter element to search:");
        String search = sc.nextLine();
        if(set.contains(search)) {
            System.out.println("Found");
        } else {
            System.out.println("Not Found");
        }

        System.out.println("First element: " + set.first());
        System.out.println("Last element: " + set.last());
    }
}