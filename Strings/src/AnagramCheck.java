import java.util.Arrays;
import java.util.Scanner;

public class AnagramCheck {
    // Normalize: toLowerCase and remove non-letters/digits (optional)
    private static String normalize(String s) {
        return s.toLowerCase().replaceAll("[^a-z0-9]", "");
    }

    public static boolean areAnagrams(String s1, String s2) {
        String a = normalize(s1);
        String b = normalize(s2);
        if (a.length() != b.length()) return false;

        char[] ca = a.toCharArray();
        char[] cb = b.toCharArray();
        Arrays.sort(ca);
        Arrays.sort(cb);
        return Arrays.equals(ca, cb);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter first string: ");
        String s1 = sc.nextLine();
        System.out.print("Enter second string: ");
        String s2 = sc.nextLine();

        if (areAnagrams(s1, s2)) {
            System.out.println("Anagrams");
        } else {
            System.out.println("Not anagrams");
        }
        sc.close();
    }
}