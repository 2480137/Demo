import java.util.*;

public class Duplicates {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();
        HashSet<Integer> seen = new HashSet<>();
        HashSet<Integer> duplicates = new HashSet<>();

        System.out.println("Enter number of elements:");
        int n = sc.nextInt();

        for(int i = 0; i < n; i++) {
            int x = sc.nextInt();
            if(!seen.add(x)) {
                duplicates.add(x);
            }
            list.add(x);
        }

        System.out.println("List: " + list);
        System.out.println("Duplicates: " + duplicates);
    }
}