public class SecondLargest {
    public static int secondLargest(int[] arr) {
        if (arr == null || arr.length < 2) {
            throw new IllegalArgumentException("Array must have at least two elements.");
        }

        Integer max = null;
        Integer second = null;

        for (int x : arr) {
            if (max == null || x > max) {
                second = max;
                max = x;
            } else if (x != max && (second == null || x > second)) {
                second = x;
            }
        }

        if (second == null) {
            throw new IllegalArgumentException("No distinct second largest element (all values equal?).");
        }
        return second;
    }

    public static void main(String[] args) {
        int[] a = {7, 7, 3, 9, 9, 5, 5};
        System.out.println("Second largest: " + secondLargest(a)); // 7
    }
}