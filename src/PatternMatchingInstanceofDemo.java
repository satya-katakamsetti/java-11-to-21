import java.util.List;
import java.util.Map;

public class PatternMatchingInstanceofDemo {
/*
     * Java Pattern Matching for `instanceof` (introduced in Java 14, stable in Java 16)
     * simplifies a common coding pattern: checking an object's type, casting it,
     * and then using it.
     *
     * Key points:
     *  - Combines the type check (`instanceof`) and the cast into a single operation.
     *  - Introduces a "pattern variable" (e.g., `s` in `obj instanceof String s`).
     *  - The pattern variable is automatically in scope within the `if` block.
     *  - Reduces boilerplate, making code cleaner and less error-prone.
     *  - The pattern variable's scope is intelligently handled in `if-else` and
     *    logical expressions (`&&`, `||`).
     *
     * Example:
     *   // Old way
     *   if (obj instanceof String) {
     *       String s = (String) obj;
     *       System.out.println(s.toUpperCase());
     *   }
     *
     *   // New way
     *   if (obj instanceof String s) {
     *       System.out.println(s.toUpperCase());
     *   }
     *
     * Purpose:
     *   To eliminate redundant code and improve the readability and safety of
     *   type-checking and casting operations.
     */

    public static void main(String[] args) {
        // Pattern Matching for instanceof - Java 16

        // ─────────────────────────────────────────────────────────────────
        // OLD WAY - Redundant cast
        // ─────────────────────────────────────────────────────────────────

        Object obj = "Hello World";

        if (obj instanceof String) {
            String s = (String) obj;  // Redundant cast!
            System.out.println("Old way: " + s.toUpperCase());
        }

        // ─────────────────────────────────────────────────────────────────
        // NEW WAY - Pattern variable
        // ─────────────────────────────────────────────────────────────────

        if (obj instanceof String s) {
            System.out.println("New way: "+ s.toUpperCase());
        }

        // ─────────────────────────────────────────────────────────────────
        // COMBINING WITH LOGICAL OPERATORS
        // ─────────────────────────────────────────────────────────────────

        // Pattern variable works with &&
        if (obj instanceof String s && s.length() > 5) {
            System.out.println("Long string: " + s);
        }

        // With negation - variable scoped to else branch
        Object maybeNumber = 42;
        if (!(maybeNumber instanceof Integer i)) {
            System.out.println("Not an integer");
        } else {
            System.out.println("Integer value: " + i);
        }

        // ─────────────────────────────────────────────────────────────────
        // REAL-WORLD: Processing Different Message Types
        // ─────────────────────────────────────────────────────────────────

        List<Object> messages = List.of(
                new TextPayload("MSG-001", "Hello World"),
                new JsonPayload("MSG-002", Map.of("key", "value")),
                new BinaryPayload("MSG-003", new byte[]{1, 2, 3, 4, 5}),
                "Unknown message format"
        );

        System.out.println("\nProcessing JMS messages:");
        for (Object msg : messages) {
            if (msg instanceof TextPayload text && !text.content().isEmpty()) {
                System.out.println("  Text: " + text.content());
            } else if (msg instanceof JsonPayload json) {
                System.out.println("  JSON with " + json.data().size() + " fields");
            } else if (msg instanceof BinaryPayload binary) {
                System.out.println("  Binary: " + binary.data().length + " bytes");
            } else {
                System.out.println("  Unknown: " + msg);
            }
        }
    }
}

record TextPayload(String id, String content) {}
record JsonPayload(String id, Map<String, Object> data) {}
record BinaryPayload(String id, byte[] data) {}
