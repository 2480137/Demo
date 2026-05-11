import java.util.Optional;

public class OptionalDemo {
    public static void main(String[] args) {
        // 1. of() - Use when you are CERTAIN the value isn't null
        Optional<String> name = Optional.of("Samarth");

        // 2. ofNullable() - Use when the value might be null
        String nullableValue = null;
        Optional<String> maybeName = Optional.ofNullable(nullableValue);

        // 3. empty() - Manually create an empty box
        Optional<String> emptyBox = Optional.empty();

        // 4. isPresent() & get() - The traditional check-then-act approach
        if (name.isPresent()) {
            System.out.println("Value found: " + name.get());
        }

        // 5. ifPresent() - The cleaner, functional way to act on a value
        name.ifPresent(val -> System.out.println("Greeting: Hello, " + val));

        // 6. orElse() - Provide a safety net (default value)
        String finalValue = maybeName.orElse("Default User");
        System.out.println("Result of orElse: " + finalValue);

        // 7. map() - Transform the value inside the box if it exists
        Optional<Integer> nameLength = name.map(String::length);
        nameLength.ifPresent(len -> System.out.println("Length of name: " + len));
    }
}