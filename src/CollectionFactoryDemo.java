import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CollectionFactoryDemo {
/*
     * Java Collection Factory Methods (introduced in Java 9, enhanced in Java 10)
     * provide a concise way to create immutable (unmodifiable) collections.
     *
     * Key points:
     *  - Creates fixed-size, immutable lists, sets, and maps.
     *  - Methods like List.of(), Set.of(), and Map.of() are simple and expressive.
     *  - Java 10 added .copyOf() methods to create immutable copies from existing collections.
     *  - Java 10 also added Collectors.toUnmodifiableList/Set/Map for use in streams.
     *  - These collections are more memory-efficient and safer in multi-threaded contexts.
     *  - IMPORTANT: They strictly reject `null` elements, keys, or values.
     *
     * Example:
     *   List<String> immutableList = List.of("one", "two", "three");
     *
     * Purpose:
     *   To easily create safe, read-only collections without boilerplate, preventing
     *   accidental modification and clearly signaling intent.
     */

    void run() {
        // Collection Factory Methods Java 9-11

        // List.of() - Immutable lists

        var list = List.of("a", "b");

        // Set.of

        var set = Set.of("a", "a", "b");

        // Map.of

        var map = Map.of(
                "key1", "value1",
                "key2", "value2"
        );

        // List.copyOf(), Set.copyOf(), Map.copyOf()  - Java 10

        var immutableList = List.copyOf(list);

        // Collectors.toUnmodifiableList

        var immutableResult = List.of(1, 2, 3, 4, 5)
                .stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toUnmodifiableList());

//        Use List.of(), Set.of(), Map.of() for constant collections
//        Use List.copyOf() to create defensive copies
//        Use Collectors.toUnmodifiable*() in streams
//        Remember: These are IMMUTABLE and reject null!

    }
}
