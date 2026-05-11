import java.util.*;

public class LinkedHashMapDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();

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

        System.out.println("LinkedHashMap: " + map);

        System.out.println("Enter key to remove:");
        String removeKey = sc.nextLine();
        map.remove(removeKey);
        System.out.println("After removal: " + map);

        System.out.println("Enter key to search:");
        String searchKey = sc.nextLine();
        if(map.containsKey(searchKey)) {
            System.out.println("Found, value: " + map.get(searchKey));
        } else {
            System.out.println("Not Found");
        }

        map.put("ExtraKey", 999);
        System.out.println("After adding extra entry: " + map);
    }
}