public class GreatestNumber {
    public static void main(String[] args) {

        int[] a = {9, 3, 6, 4, 8, 5};

        int max = a[0];  // assume first as greatest

        for (int i = 1; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
            }
        }

        System.out.println("Greatest number = " + max);
    }
}