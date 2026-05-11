import java.util.*;

public class RemoveDuplicatesUsingSet {
    public static int[] removeDuplicates(int[] arr) {
        if (arr == null) return null;

        Set<Integer> seen = new LinkedHashSet<>();
        for (int num : arr) {
            seen.add(num);
        }

        int[] result = new int[seen.size()];
        int i = 0;
        for (int num : seen) {
            result[i++] = num;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 2, 4, 2, 3, 5, 1};
        int[] unique = removeDuplicates(arr);
        System.out.println("Unique (order preserved): " + Arrays.toString(unique));
    }
}