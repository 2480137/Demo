public class AlternateElements {
    public static void main(String[] args) {

        String[] a = {"One", "Two", "Three", "Four"};

        System.out.println("Alternate elements in the array:");

        for (int i = 0; i < a.length; i += 2) {
            System.out.println(a[i]);
        }
    }
}