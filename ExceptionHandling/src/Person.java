import java.util.Arrays;

public final class Person {
    private final String name;
    private final int[] scores;

    public Person(String name, int[] scores) {
        this.name = name;
        this.scores = scores.clone();
    }

    public String getName() { return name; }
    public int[] getScores() { return scores.clone(); }

    @Override public String toString() { return name + " " + Arrays.toString(scores); }
}