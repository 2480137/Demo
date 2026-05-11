public class StringPattern {
    public static void main(String[] args) {
        String s1 = "Samarth";
        StringBuilder sb = new StringBuilder(s1);

        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (i % 2 == 0) {
                sb.setCharAt(i, Character.toUpperCase(c)); // even index -> UPPER
            } else {
                sb.setCharAt(i, Character.toLowerCase(c)); // odd index -> lower
            }
        }

        String result = sb.toString();
        System.out.println(result); // Output: SaMaRtH
    }
}