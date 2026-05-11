import java.util.*;
import java.util.regex.*;

public class WordFrequency {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a paragraph (press ENTER on an empty line to finish):");

        // Read multiple lines until an empty line
        StringBuilder sb = new StringBuilder();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.trim().isEmpty()) break; // stop on empty line
            sb.append(line).append(" ");
        }
        String paragraph = sb.toString();

        // 1) Normalize: lowercase for case-insensitive counting
        String lower = paragraph.toLowerCase();

        // 2) Tokenize words (Unicode-aware): \p{L} matches any letter in any language, \p{Nd} for digits (optional)
        // If you want to count only alphabetical words, use "\\p{L}+"
        Pattern wordPattern = Pattern.compile("\\p{L}+(?:'\\p{L}+)?");
        // The (?:'\\p{L}+)? part keeps simple contractions like don't, it's, Samarth's as single tokens

        Map<String, Integer> freq = new HashMap<>();
        Matcher m = wordPattern.matcher(lower);
        while (m.find()) {
            String word = m.group();
            freq.put(word, freq.getOrDefault(word, 0) + 1);
        }

        // 3) Sort by frequency (desc), then word (asc)
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(freq.entrySet());
        entries.sort((a, b) -> {
            int cmp = Integer.compare(b.getValue(), a.getValue()); // freq desc
            return (cmp != 0) ? cmp : a.getKey().compareTo(b.getKey()); // word asc
        });

        // 4) Print
        System.out.println("\nWord frequencies:");
        for (Map.Entry<String, Integer> e : entries) {
            System.out.printf("%-20s %d%n", e.getKey(), e.getValue());
        }

        sc.close();
    }
}