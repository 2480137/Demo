import java.util.*;

public class HashSetDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashSet<String> set = new HashSet<>();

        System.out.println("Enter number of elements:");
        int n = sc.nextInt();
        sc.nextLine();

        for(int i = 0; i < n; i++) {
            System.out.println("Enter element:");
            set.add(sc.nextLine());
        }

        System.out.println("HashSet: " + set);

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
    }
}