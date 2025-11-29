import java.util.List;
import java.util.Map;

public class PatternMatchingSwitchDemo {
/*
     * Java Pattern Matching for `switch` (stable in Java 21) revolutionizes how
     * we write switch statements and expressions, making them far more powerful
     * and expressive.
     *
     * Key points:
     *  - Type Patterns: `switch` can now branch on the type of an object, binding
     *    it to a pattern variable (e.g., `case String s:`).
     *  - Guarded Patterns: Use a `when` clause to add a condition to a case
     *    (e.g., `case Integer i when i > 0:`).
     *  - Null Handling: `case null` is now a valid and explicit case label.
     *  - Exhaustiveness with Sealed Types: The compiler can verify that a `switch`
     *    over a sealed class/interface covers all permitted subtypes, eliminating
     *    the need for a `default` case. This is a major safety improvement.
     *
     * Example:
     *   static String formatValue(Object obj) {
     *       return switch (obj) {
     *           case null      -> "null";
     *           case String s  -> "String: " + s;
     *           case Integer i -> "Integer: " + i;
     *           default       -> "Unknown";
     *       };
     *   }
     *
     * Purpose:
     *   To drastically reduce the boilerplate of complex `if-else if-else` chains,
     *   improve code clarity, and enhance compile-time safety, especially when
     *   dealing with complex data models (like sealed hierarchies).
     */

    // Introduced in Java 21

    public static void main(String[] args) {
        // ─────────────────────────────────────────────────────────────────
        // TYPE PATTERNS IN SWITCH
        // ─────────────────────────────────────────────────────────────────

        System.out.println("Type patterns in switch:");
        List<Object> items = List.of("Hello", 42, 3.14, List.of(1, 2, 3), "null");

        for (Object item : items) {
            String result = formatValue(item);
            System.out.println("  " + item + " -> " + result);
        }

        // ─────────────────────────────────────────────────────────────────
        // GUARDED PATTERNS (when clause)
        // ─────────────────────────────────────────────────────────────────

        System.out.println("\nGuarded patterns (when clause):");
        List<Integer> numbers = List.of(-5, 0, 7, 42, 100, 1000);

        for (Integer n : numbers) {
            String category = categorize(n);
            System.out.printf("  %d -> %s%n", n, category);
        }

        // ─────────────────────────────────────────────────────────────────
        // EXHAUSTIVENESS WITH SEALED CLASSES
        // ─────────────────────────────────────────────────────────────────

        System.out.println("\nExhaustive matching with sealed classes:");
        List<PaymentMethod> payments = List.of(
                new CreditCard("John", "4111111111111111"),
                new BankTransfer("IBAN12345", "SWIFT789"),
                new DigitalWallet("john@paypal.com")
        );

        for (PaymentMethod payment : payments) {
            String processed = processPayment(payment);
            System.out.println("  " + processed);
        }

        // ─────────────────────────────────────────────────────────────────
        // REAL-WORLD: Message Processing (Your ActiveMQ use case!)
        // ─────────────────────────────────────────────────────────────────

        System.out.println("\nReal-world message processing:");
        List<JmsMessage> messages = List.of(
                new TextMsg("MSG-001", "Hello World", Priority.NORMAL),
                new TextMsg("MSG-002", "URGENT!", Priority.HIGH),
                new BytesMsg("MSG-003", new byte[]{1, 2, 3}),
                new MapMsg("MSG-004", Map.of("action", "process")),
                new CommandMsg("MSG-005", JmsCommand.SHUTDOWN)
        );

        for (JmsMessage msg : messages) {
            handleMessage(msg);
        }


    }

    static String formatValue(Object obj) {
        return switch (obj) {
            case null -> "null values";
            case String s -> "String of length " + s.length();
            case Integer i -> "Integer: " + i;
            case Double d -> "Double: " + String.format("%.2f", d);
            case List<?> list -> "List with " + list.size() + " elements";
            default -> "Unknown: " + obj.getClass().getSimpleName();
        };
    }

    static String categorize(Integer n) {
        return switch (n) {
            case null -> "null";
            case Integer i when i < 0 -> "negative";
            case Integer i when i == 0 -> "zero";
            case Integer i when i <= 10 -> "small positive";
            case Integer i when i <= 100 -> "medium positive";
            case Integer i -> "large positive: " + i;
        };
    }

    static String processPayment(PaymentMethod payment) {
        return switch (payment) {
            case CreditCard cc -> "Credit card: " + cc.maskedNumber();
            case BankTransfer bt -> "Bank transfer to: " + bt.iban();
            case DigitalWallet dw -> "Digital wallet: " + dw.email();
            // No default needed - sealed interface!
        };
    }

    static void handleMessage(JmsMessage msg) {
        switch (msg) {
            case TextMsg t when t.priority() == Priority.HIGH -> {
                System.out.println("  ⚡ HIGH PRIORITY: " + t.content());
            }
            case TextMsg t -> {
                System.out.println("  📝 Text: " + t.content());
            }
            case BytesMsg b -> {
                System.out.println("  📦 Bytes: " + b.data().length + " bytes");
            }
            case MapMsg m -> {
                System.out.println("  🗺️ Map: " + m.properties());
            }
            case CommandMsg c -> {
                System.out.println("  ⚙️ Command: " + c.command());
            }
        }
    }

}

sealed interface PaymentMethod permits CreditCard, BankTransfer, DigitalWallet {}
record CreditCard(String name, String number) implements PaymentMethod {
    String maskedNumber() { return "****" + number.substring(number.length() - 4); }
}
record BankTransfer(String iban, String swift) implements PaymentMethod {}
record DigitalWallet(String email) implements PaymentMethod {}

// JMS Message types
sealed interface JmsMessage permits TextMsg, BytesMsg, MapMsg, CommandMsg {
    String id();
}
enum Priority { LOW, NORMAL, HIGH, CRITICAL }
enum JmsCommand { START, STOP, PAUSE, RESUME, SHUTDOWN }
record TextMsg(String id, String content, Priority priority) implements JmsMessage {}
record BytesMsg(String id, byte[] data) implements JmsMessage {}
record MapMsg(String id, Map<String, Object> properties) implements JmsMessage {}
record CommandMsg(String id, JmsCommand command) implements JmsMessage {}