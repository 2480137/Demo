public class ReverseArray {
    public static void main(String[] args) {
        int[] a = {9, 3, 6, 8, 4, 7};

        System.out.println("Array elements in reverse order:");
        for (int i = a.length - 1; i >= 0; i--) {
            System.out.print(a[i] + (i == 0 ? "" : " "));
        }
        System.out.println(); // newline at the end (optional)
    }
}