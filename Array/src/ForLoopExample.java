public class ForLoopExample {
    public static void main(String[] args) {

        int[] a = {10, 20, 30, 40, 50};

        System.out.println("Using normal for loop:");

        for (int i = 0; i < a.length; i++) {
            System.out.println("Element at index " + i + " = " + a[i]);
        }
    }
}