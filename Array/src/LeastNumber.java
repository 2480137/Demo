public class LeastNumber {
    public static void main(String[] args) {

        int[] a = {9, 3, 6, 4, 8, 5};

        int min = a[0];  // assume first as least

        for (int i = 1; i < a.length; i++) {
            if (a[i] < min) {
                min = a[i];
            }
        }

        System.out.println("Least number = " + min);
    }
}