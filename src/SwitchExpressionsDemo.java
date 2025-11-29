import java.util.List;

public class SwitchExpressionsDemo {
/*
     * Java Switch Expressions (introduced in Java 12, stable in Java 14) upgrade
     * the traditional `switch` statement into a more concise, safer, and more
     * powerful expression that returns a value.
     *
     * Key points:
     *  - Returns a value: A switch expression can be assigned to a variable.
     *  - Arrow syntax (`->`): For single-line cases, no `break` is needed, which
     *    prevents accidental fall-through bugs.
     *  - Multiple case labels: `case "A", "B", "C" -> ...` simplifies branching.
     *  - Exhaustiveness: For enums, sealed types, or if a `default` is present,
     *    the compiler ensures all possible cases are handled.
     *  - `yield` keyword: Used to return a value from a multi-line block within
     *    a case.
     *
     * Example:
     *   var result = switch (day) {
     *       case "MONDAY", "FRIDAY" -> "Weekday";
     *       case "SATURDAY", "SUNDAY" -> "Weekend";
     *       default -> "Midweek";
     *   };
     *
     * Purpose:
     *   To modernize `switch`, making it less verbose, less error-prone, and
     *   more aligned with functional programming patterns by allowing it to be
     *   used as an expression.
     */
    public static void main(String[] args) {
        // Switch Expressions - Java 14

        // ─────────────────────────────────────────────────────────────────
        // OLD SWITCH STATEMENT - Verbose and error-prone
        // ─────────────────────────────────────────────────────────────────

        var day = "MONDAY";
        String oldResult;
        switch (day) {
            case "MONDAY":
            case "TUESDAY":
            case "WEDNESDAY":
            case "THURSDAY":
            case "FRIDAY":
                oldResult = "Weekday";
                break;  // Forget this = bug!
            case "SATURDAY":
            case "SUNDAY":
                oldResult = "Weekend";
                break;
            default:
                oldResult = "Unknown";
        }

        // ─────────────────────────────────────────────────────────────────
        // NEW SWITCH EXPRESSION with Arrow Syntax
        // ─────────────────────────────────────────────────────────────────

        var newResult = switch (day) {
            case "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY" -> "Weekday";
            case "SATURDAY", "SUNDAY" -> "Weekend";
            default -> "Unknown";
        };
        System.out.println(day + " is a " + newResult);

        // ─────────────────────────────────────────────────────────────────
        // SWITCH EXPRESSION - Returns a value!
        // ─────────────────────────────────────────────────────────────────

        var month = 2;
        int daysInMonth = switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> 32;
            case 4, 6, 9, 11 -> 30;
            case 2 -> 28;
            default -> throw new IllegalArgumentException("Invalid month: " + month);
        };

        System.out.println("Days in month " + month + ": " + daysInMonth);

        // ─────────────────────────────────────────────────────────────────
        // YIELD keyword - For blocks that need computation
        // ─────────────────────────────────────────────────────────────────

        var status = "PENDING";
        int priority = switch (status) {
            case "CRITICAL" -> 1;
            case "HIGH" -> 2;
            case "MEDIUM" -> {
                System.out.println("  Processing MEDIUM status...");
                yield 3;  // yield returns value from block
            }
            case "LOW" -> 4;
            case "PENDING" -> {
                // Complex logic here
                var basePriority = 5;
                var adjustment = 0;
                yield basePriority + adjustment;
            }
            default -> 10;
        };
        System.out.println("Priority for " + status + ": " + priority);

        // ─────────────────────────────────────────────────────────────────
        // ENUM SWITCH - Exhaustiveness checking
        // ─────────────────────────────────────────────────────────────────

        var messageType = MessageType.ORDER;

        String queue = switch (messageType) {
            case ORDER -> "orders-queue";
            case PAYMENT -> "payments-queue";
            case NOTIFICATION -> "notifications-queue";
            // No default needed - compiler knows all cases covered!
        };
        System.out.println("Route " + messageType + " to: " + queue);

        // ─────────────────────────────────────────────────────────────────
        // REAL-WORLD: Message Routing (Your ActiveMQ use case!)
        // ─────────────────────────────────────────────────────────────────

        var messageTypes = List.of("ORDER_CREATED", "PAYMENT_RECEIVED", "INVENTORY_LOW", "UNKNOWN");

        for (var type : messageTypes) {
            var routingInfo = switch (type) {
                case "ORDER_CREATED", "ORDER_UPDATED", "ORDER_CANCELLED" ->
                        new RoutingInfo("orders.queue", "order-processor", 1);
                case "PAYMENT_RECEIVED", "PAYMENT_FAILED" ->
                        new RoutingInfo("payments.queue", "payment-processor", 1);
                case "INVENTORY_LOW", "INVENTORY_UPDATE" ->
                        new RoutingInfo("inventory.queue", "inventory-processor", 2);
                default -> {
                    System.out.println("Unknown message type: " + type + " -> DLQ");
                    yield new RoutingInfo("dlq", "dlq-processor", 10);
                }
            };
            System.out.println("  Route " + type + " -> " + routingInfo.queue());
        }

    }
}

enum MessageType {
    ORDER,
    PAYMENT,
    NOTIFICATION;
}

record RoutingInfo(String queue, String processor, int count) {

}