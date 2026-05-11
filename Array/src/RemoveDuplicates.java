import java.util.*;

public class RemoveDuplicates {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input array size
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        int[] arr = new int[n];

        // Input elements
        System.out.println("Enter " + n + " elements (duplicates allowed):");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // Use LinkedHashSet to preserve insertion order
        Set<Integer> set = new LinkedHashSet<>();
        for (int x : arr) {
            set.add(x);
        }

        // Convert Set back to array (optional)
        int[] unique = new int[set.size()];
        int i = 0;
        for (int x : set) {
            unique[i++] = x;
        }

        // Output
        System.out.println("Array after removing duplicates (order preserved): " + Arrays.toString(unique));

        sc.close();
    }
}