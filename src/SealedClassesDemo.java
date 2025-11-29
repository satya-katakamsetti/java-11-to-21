import java.util.List;
import java.util.Map;

public class SealedClassesDemo {
    /*
     * Java Sealed Classes and Interfaces (introduced in Java 15, stable in Java 17)
     * provide fine-grained control over inheritance, allowing a superclass to
     * explicitly declare which classes are permitted to extend or implement it.
     *
     * Key points:
     *  - A `sealed` class or interface uses the `permits` clause to list its
     *    allowed direct subtypes.
     *  - Permitted subtypes must be `final`, `sealed`, or `non-sealed`.
     *    - `final`: Cannot be extended further.
     *    - `sealed`: Can be extended, but only by types it permits.
     *    - `non-sealed`: Reverts to traditional inheritance; any class can extend it.
     *  - This enables the creation of "closed" and well-defined class hierarchies.
     *  - It is a cornerstone for exhaustive pattern matching in `switch` statements,
     *    as the compiler knows all possible subtypes.
     *
     * Example:
     *   public sealed interface Shape permits Circle, Square {}
     *   public final record Circle(double r) implements Shape {}
     *   public final record Square(double s) implements Shape {}
     *
     * Purpose:
     *   To model business domains more accurately and safely, and to enable
     *   powerful, compile-time checked pattern matching that eliminates the
     *   need for a `default` case in many scenarios.
     */

    public void run() {
        // ─────────────────────────────────────────────────────────────────
        // BASIC SEALED HIERARCHY
        // ─────────────────────────────────────────────────────────────────

        System.out.println("Sealed class hierarchy - Shape:");
        List<Shape> shapes = List.of(
                new Circle(5.0),
                new Square(4.0),
                new Triangle(3.0, 4.0, 5.0)
        );

        for (Shape shape : shapes) {
            System.out.printf("  %s: area = %.2f%n",
                    shape.getClass().getSimpleName(),
                    shape.area());
        }

        // ─────────────────────────────────────────────────────────────────
        // SEALED INTERFACE WITH RECORDS
        // ─────────────────────────────────────────────────────────────────

        System.out.println("\nSealed interface - Result<T>:");

        Result<String> success = new Success<>("Operation completed!");
        Result<String> failure = new Failure<>(new RuntimeException("Something went wrong"));

        processResult(success);
        processResult(failure);

        // ─────────────────────────────────────────────────────────────────
        // REAL-WORLD: Message Types for JMS (Your use case!)
        // ─────────────────────────────────────────────────────────────────

        System.out.println("\nSealed message types:");
        List<QueueMessage> queueMessages = List.of(
                new TextMessage("MSG-001", "Hello World"),
                new BytesMessage("MSG-002", new byte[]{1, 2, 3}),
                new MapMessage("MSG-003", Map.of("key", "value")),
                new CommandMessage("MSG-004", Command.SHUTDOWN)
        );

        for (QueueMessage msg : queueMessages) {
            processQueueMessage(msg);
        }
    }

    void processResult(Result<String> result) {
        // With sealed types, compiler knows all possible cases!
        String output;
        if (result instanceof Success<String> s) {
            output = "✅ Success: " + s.value();
        } else if (result instanceof Failure<String> f) {
            output = "❌ Failure: " + f.exception().getMessage();
        } else {
            output = "Unknown";  // Never reached - compiler knows!
        }
        System.out.println("  " + output);
    }

    void processQueueMessage(QueueMessage msg) {
        // Handle each message type
        String result;
        if (msg instanceof TextMessage t) {
            result = "Text: " + t.content();
        } else if (msg instanceof BytesMessage b) {
            result = "Bytes: " + b.data().length + " bytes";
        } else if (msg instanceof MapMessage m) {
            result = "Map: " + m.properties().size() + " entries";
        } else if (msg instanceof CommandMessage c) {
            result = "Command: " + c.command();
        } else {
            result = "Unknown";
        }
        System.out.println("  " + msg.messageId() + " -> " + result);
    }

    int getStatusCode(ApiResult<?> result) {
        if (result instanceof Found<?>) return 200;
        if (result instanceof NotFound<?>) return 404;
        if (result instanceof ApiError<?> e) return e.code();
        return 500;
    }

}

// Sealed class
sealed abstract class Shape permits Circle, Square, Triangle {
    abstract double area();
}

final class Circle extends Shape {
    private final double radius;
    Circle(double radius) {
        this.radius = radius;
    }

    @Override double area() { return Math.PI * radius * radius; }
}

final class Square extends Shape {
    private final double side;
    Square(double side) { this.side = side; }
    @Override double area() { return side * side; }
}

final class Triangle extends Shape {
    private final double a, b, c;
    Triangle(double a, double b, double c) { this.a = a; this.b = b; this.c = c; }
    @Override double area() {
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }
}

// Sealed interface with records
sealed interface Result<T> permits Success, Failure {}
record Success<T>(T value) implements Result<T> {}
record Failure<T>(Exception exception) implements Result<T> {}

// Sealed interface for message types
sealed interface QueueMessage permits TextMessage, BytesMessage, MapMessage, CommandMessage {
    String messageId();
}
record TextMessage(String messageId, String content) implements QueueMessage {}
record BytesMessage(String messageId, byte[] data) implements QueueMessage {}
record MapMessage(String messageId, Map<String, Object> properties) implements QueueMessage {}
record CommandMessage(String messageId, Command command) implements QueueMessage {}
enum Command { START, STOP, PAUSE, RESUME, SHUTDOWN }

// Sealed interface for API responses
sealed interface ApiResult<T> permits Found, NotFound, ApiError {}
record Found<T>(T data) implements ApiResult<T> {}
record NotFound<T>(String message) implements ApiResult<T> {}
record ApiError<T>(int code, String message) implements ApiResult<T> {}