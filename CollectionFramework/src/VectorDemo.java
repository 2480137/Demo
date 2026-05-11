import java.util.*;

public class VectorDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Vector<String> vector = new Vector<>();

        System.out.println("Enter number of elements:");
        int n = sc.nextInt();
        sc.nextLine();

        for(int i = 0; i < n; i++) {
            System.out.println("Enter element:");
            vector.add(sc.nextLine());
        }

        System.out.println("Vector: " + vector);

        System.out.println("Enter element to remove:");
        String remove = sc.nextLine();
        vector.remove(remove);
        System.out.println("After removal: " + vector);

        System.out.println("Enter element to search:");
        String search = sc.nextLine();
        if(vector.contains(search)) {
            System.out.println("Found");
        } else {
            System.out.println("Not Found");
        }

        vector.add("ExtraItem");
        System.out.println("After adding an item: " + vector);
    }
}