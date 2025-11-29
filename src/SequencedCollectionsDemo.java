import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.SequencedMap;
import java.util.SequencedSet;
import java.util.Set;
import java.util.TreeSet;

public class SequencedCollectionsDemo {
/*
     * Java Sequenced Collections (introduced in Java 21) provide a unified API
     * for collections that have a well-defined encounter order.
     *
     * Key points:
     *  - New interfaces: `SequencedCollection`, `SequencedSet`, `SequencedMap`.
     *  - Provides common methods to access the first and last elements:
     *    - `getFirst()`, `getLast()`
     *    - `addFirst()`, `addLast()`
     *    - `removeFirst()`, `removeLast()`
     *  - The `reversed()` method provides a reverse-ordered view of the collection.
     *  - Retrofitted to existing collections like `ArrayList`, `LinkedHashSet`,
     *    `LinkedHashMap`, and `TreeSet`.
     *
     * Example:
     *   List<String> list = new ArrayList<>(List.of("a", "b", "c"));
     *   System.out.println(list.getFirst()); // Prints "a"
     *   list.addFirst("x"); // list is now ["x", "a", "b", "c"]
     *
     * Purpose:
     *   To standardize access to first/last elements across ordered collections,
     *   eliminating the need for type-specific boilerplate (e.g., `list.get(0)`,
     *   `deque.getFirst()`, `treeSet.first()`).
     */

    public static void main(String[] args) {
        SequencedCollectionsDemo demo = new SequencedCollectionsDemo();
        demo.sequencedCollectionBasics();
        demo.sequencedSet();
    }

    void sequencedCollectionBasics() {
        System.out.println("1. sequencedCollection basics: ");

        List<String> list = new ArrayList<>(List.of("first", "second", "third"));

        System.out.println(" Original: "+ list);
        System.out.println(" getFirst(): "+ list.getFirst());
        System.out.println(" getLast(): "+ list.getLast());

        list.addFirst("new-first");
        list.addLast("new-last");
        System.out.println("  After addFirst/addLast: " + list);

        String removedFirst = list.removeFirst();
        String removedLast = list.removeLast();
        System.out.println("  Removed: " + removedFirst + ", " + removedLast);
        System.out.println("  After removal: " + list);
    }

    void sequencedSet() {
        System.out.println("\n2. SequencedSet:");

        // LinkedHashSet implements SequencedSet
        SequencedSet<String> set = new LinkedHashSet<>();
        set.add("alpha");
        set.add("beta");
        set.add("gamma");

        System.out.println("  Set: " + set);
        System.out.println("  getFirst(): " + set.getFirst());
        System.out.println("  getLast(): " + set.getLast());

        // addFirst moves existing element to front
        set.addFirst("gamma");
        System.out.println("  After addFirst('gamma'): " + set);

        // TreeSet also implements SequencedSet
        SequencedSet<Integer> sorted = new TreeSet<>(Set.of(5, 2, 8, 1, 9));
        System.out.println("  TreeSet: " + sorted);
        System.out.println("  First (min): " + sorted.getFirst());
        System.out.println("  Last (max): " + sorted.getLast());
    }

    void sequencedMap() {
        System.out.println("\n3. SequencedMap:");

        // LinkedHashMap implements SequencedMap
        SequencedMap<String, Integer> map = new LinkedHashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        System.out.println("  Map: " + map);
        System.out.println("  firstEntry(): " + map.firstEntry());
        System.out.println("  lastEntry(): " + map.lastEntry());

        map.putFirst("zero", 0);
        map.putLast("four", 4);
        System.out.println("  After putFirst/putLast: " + map);

        // Poll removes and returns
        var polledFirst = map.pollFirstEntry();
        var polledLast = map.pollLastEntry();
        System.out.println("  Polled: " + polledFirst + ", " + polledLast);
        System.out.println("  After polling: " + map);
    }

}
