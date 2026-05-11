import java.util.*;

public class BinarySearch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input array size
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        int[] arr = new int[n];

        // Input elements (in any order)
        System.out.println("Enter " + n + " elements (in any order):");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // Sort ascending for binary search
        Arrays.sort(arr);
        System.out.println("Sorted array (ascending): " + Arrays.toString(arr));

        // Input key to search
        System.out.print("Enter the element to search: ");
        int key = sc.nextInt();

        int result = binarySearch(arr, key);

        if (result == -1) {
            System.out.println("Element not found in the array.");
        } else {
            System.out.println("Element found at index (in sorted array): " + result);
        }

        sc.close();
    }

    // Binary Search method (iterative, ascending)
    public static int binarySearch(int[] arr, int key) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == key) {
                return mid;
            } else if (arr[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
