import java.util.*;

public class ArrayListDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();

        System.out.println("Enter number of items:");
        int n = sc.nextInt();
        sc.nextLine();

        for(int i = 0; i < n; i++) {
            System.out.println("Enter item:");
            list.add(sc.nextLine());
        }

        System.out.println("ArrayList: " + list);

        System.out.println("Enter item to remove:");
        String removeItem = sc.nextLine();
        list.remove(removeItem);

        System.out.println("After removal: " + list);

        System.out.println("Enter item to search:");
        String search = sc.nextLine();
        if(list.contains(search)) {
            System.out.println("Found");
        } else {
            System.out.println("Not Found");
        }
    }
}