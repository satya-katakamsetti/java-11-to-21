import java.time.Instant;
import java.util.List;
import java.util.Map;

public class RecordsDemo {

    /*
     * Java Records (introduced in Java 14, stable since Java 16) are a concise way
     * to create immutable data-carrier classes without boilerplate.
     *
     * Key points:
     *  - Automatically generate: constructors, equals(), hashCode(), toString()
     *  - Fields are implicitly private and final → true immutability
     *  - Ideal for DTOs, API models, value objects, and simple data holders
     *  - Great with pattern matching and modern Java features
     *  - Eliminates the need for Lombok @Data/@Value for basic data classes
     *
     * Example:
     *   public record Person(String name, int age) { }
     *
     * Purpose:
     *   Clean, explicit, immutable data structures with minimal code.
     */

    public static void main(String[] args) {
        var point = new Point(10, 20);
        System.out.println("Record created: " + point);
        System.out.println("Accessor x(): " + point.x());  // Not getX()!
        System.out.println("Accessor y(): " + point.y());


        // ─────────────────────────────────────────────────────────────────
        // AUTO-GENERATED METHODS
        // ─────────────────────────────────────────────────────────────────

        var point1 = new Point(10, 20);
        var point2 = new Point(10, 20);
        var point3 = new Point(30, 40);

        System.out.println("\nAuto-generated methods:");
        System.out.println("  toString(): " + point1);
        System.out.println("  equals(): point1.equals(point2) = " + point1.equals(point2));
        System.out.println("  hashCode(): point1.hashCode() == point2.hashCode() = " +
                (point1.hashCode() == point2.hashCode()));

        // ─────────────────────────────────────────────────────────────────
        // RECORD WITH VALIDATION (Compact Constructor)
        // ─────────────────────────────────────────────────────────────────

        // record User(String name, String email, int age) { ... } -- defined below

        try {
            var invalidUser = new User("", "test@test.com", 25);
        } catch (IllegalArgumentException e) {
            System.out.println("\n✓ Validation works: " + e.getMessage());
        }

        var validUser = new User("  SATYA  ", "SATYA@example.com", 30);
        System.out.println("Normalized user: " + validUser);
        System.out.println("  name: '" + validUser.name() + "'");  // Trimmed and lowercased

        // record Rectangle(double width, double height) { ... } -- defined below

        var rect = new Rectangle(10.0, 5.0);
        System.out.println("\nRecord with custom methods:");
        System.out.println("  Rectangle: " + rect);
        System.out.println("  area(): " + rect.area());
        System.out.println("  perimeter(): " + rect.perimeter());
        System.out.println("  isSquare(): " + rect.isSquare());

        // ─────────────────────────────────────────────────────────────────
        // RECORD WITH STATIC FACTORY METHODS
        // ─────────────────────────────────────────────────────────────────

        var message1 = Message.of("Hello World");
        var message2 = Message.empty();
        System.out.println("\nStatic factory methods:");
        System.out.println("  Message.of(): " + message1);
        System.out.println("  Message.empty(): " + message2);

        // ─────────────────────────────────────────────────────────────────
        // NESTED RECORDS - Perfect for DTOs
        // ─────────────────────────────────────────────────────────────────

        var apiResponse = new ApiResponse<>(
                true,
                new Pagination(1, 10, 100),
                List.of(
                        new Message("MSG-001", "First message", Instant.now()),
                        new Message("MSG-002", "Second message", Instant.now())
                ),
                null
        );

        System.out.println("\nNested records (API Response):");
        System.out.println("  success: " + apiResponse.success());
        System.out.println("  pagination: " + apiResponse.pagination());
        System.out.println("  totalPages: " + apiResponse.pagination().totalPages());
        System.out.println("  data count: " + apiResponse.data().size());


        // ─────────────────────────────────────────────────────────────────
        // REAL-WORLD: JMS Message Payload (Your use case!)
        // ─────────────────────────────────────────────────────────────────

        var orderEvent = new OrderEvent(
                "EVT-" + System.currentTimeMillis(),
                "ORD-12345",
                OrderEvent.Type.CREATED,
                Map.of(
                        "customerId", "CUST-789",
                        "amount", "299.99",
                        "items", "3"
                ),
                Instant.now()
        );

        System.out.println("\nJMS Message Payload (OrderEvent):");
        System.out.println("  " + orderEvent);
        System.out.println("  isHighValue: " + orderEvent.isHighValue());

    }

}


// Record definitions (must be at class level in Java, or in separate files)
record Point(int x, int y) {}

record User(String name, String email, int age) {
    // Compact constructor - parameters are implicit
    public User {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be blank");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email");
        }
        if (age < 0 || age > 150) {
            throw new IllegalArgumentException("Invalid age");
        }
        // Normalize data
        name = name.trim().toLowerCase();
        email = email.trim().toLowerCase();
    }
}

record Rectangle(double width, double height) {
    // Custom instance methods
    public double area() {
        return width * height;
    }

    public double perimeter() {
        return 2 * (width + height);
    }

    public boolean isSquare() {
        return width == height;
    }
}

record Message(String id, String content, Instant timestamp) {
    // Static factory methods
    public static Message of(String content) {
        return new Message(
                "MSG-" + System.currentTimeMillis(),
                content,
                Instant.now()
        );
    }

    public static Message empty() {
        return new Message("EMPTY", "", Instant.now());
    }
}

record Pagination(int page, int pageSize, int totalItems) {
    public int totalPages() {
        return (int) Math.ceil((double) totalItems / pageSize);
    }
}

record ApiResponse<T>(boolean success, Pagination pagination, List<T> data, String error) {}

record OrderEvent(String eventId, String orderId, Type type, Map<String, String> data, Instant timestamp) {
    enum Type {CREATED, UPDATED, CANCELLED, SHIPPED}

    public boolean isHighValue() {
        var amount = data.getOrDefault("amount", "0");
        return Double.parseDouble(amount) > 100;
    }
}