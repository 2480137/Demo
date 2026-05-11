import java.util.*;

public class CompareHashMaps {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String, Integer> map1 = new HashMap<>();
        HashMap<String, Integer> map2 = new HashMap<>();

        System.out.println("Enter number of entries for first map:");
        int n1 = sc.nextInt();
        sc.nextLine();

        for(int i = 0; i < n1; i++) {
            System.out.println("Enter key:");
            String key = sc.nextLine();
            System.out.println("Enter value:");
            int value = sc.nextInt();
            sc.nextLine();
            map1.put(key, value);
        }

        System.out.println("Enter number of entries for second map:");
        int n2 = sc.nextInt();
        sc.nextLine();

        for(int i = 0; i < n2; i++) {
            System.out.println("Enter key:");
            String key = sc.nextLine();
            System.out.println("Enter value:");
            int value = sc.nextInt();
            sc.nextLine();
            map2.put(key, value);
        }

        System.out.println("Map1: " + map1);
        System.out.println("Map2: " + map2);

        if(map1.equals(map2)) {
            System.out.println("Both HashMaps are equal");
        } else {
            System.out.println("HashMaps are not equal");
        }
    }
}