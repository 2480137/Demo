public class InsertionSort {

    public static void insertionSort(int[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int key = arr[i];      // element to be inserted
            int j = i - 1;

            // Move elements greater than 'key' to one position ahead
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            // Insert the key in correct location
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {

        int[] arr = {12, 11, 13, 5, 6};

        System.out.println("Original array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }

        insertionSort(arr);

        System.out.println("\nSorted array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}