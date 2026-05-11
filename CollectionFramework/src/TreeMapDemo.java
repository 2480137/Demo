import java.util.*;

public class TreeMapDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TreeMap<String, Integer> map = new TreeMap<>();

        System.out.println("Enter number of entries:");
        int n = sc.nextInt();
        sc.nextLine();

        for(int i = 0; i < n; i++) {
            System.out.println("Enter key:");
            String key = sc.nextLine();
            System.out.println("Enter value:");
            int value = sc.nextInt();
            sc.nextLine();
            map.put(key, value);
        }

        System.out.println("TreeMap: " + map);

        System.out.println("First Key: " + map.firstKey());
        System.out.println("Last Key: " + map.lastKey());

        System.out.println("Enter key to search:");
        String search = sc.nextLine();
        if(map.containsKey(search)) {
            System.out.println("Found, value: " + map.get(search));
        } else {
            System.out.println("Not Found");
        }
    }
}