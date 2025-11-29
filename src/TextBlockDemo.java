import java.util.stream.Collectors;

public class TextBlockDemo {
/*
     * Java Text Blocks (introduced in Java 13, stable in Java 15) provide a
     * user-friendly way to declare multi-line string literals without the clutter
     * of escape sequences and concatenation.
     *
     * Key points:
     *  - A text block starts with `"""` followed by a newline and ends with `"""`.
     *  - It automatically handles newlines and preserves relative indentation.
     *  - Incidental leading whitespace is stripped, making it easy to embed formatted
     *    text (like JSON, SQL, or HTML) directly in your code.
     *  - The position of the closing `"""` determines how indentation is handled.
     *  - Can be combined with `formatted()` or `String.format()` for interpolation.
     *
     * Example:
     *   String json = """
     *       {
     *         "name": "AiDE",
     *         "role": "Debugger"
     *       }
     *       """;
     *
     * Purpose:
     *   To dramatically improve the readability and writability of multi-line
     *   strings, especially for code snippets, configuration, or structured text.
     */
    public static void main(String[] args) {
        // Text Blocks Java 15

        // ─────────────────────────────────────────────────────────────────
        // OLD WAY - Escape sequence nightmare
        // ─────────────────────────────────────────────────────────────────

        String oldJson = "{\n" +
                "  \"name\": \"John\",\n" +
                "  \"email\": \"john@example.com\",\n" +
                "  \"age\": 30\n" +
                "}";

        // ─────────────────────────────────────────────────────────────────
        // NEW WAY - Clean and readable!
        // ─────────────────────────────────────────────────────────────────

        String newJson = """
                {
                   "name": "John",
                   "email": "john@example.com",
                   "age": 30
                }
                """;
        System.out.println("JSON Text Block:\n" + newJson);

        // ─────────────────────────────────────────────────────────────────
        // SQL QUERIES - Much cleaner!
        // ─────────────────────────────────────────────────────────────────

        String sql = """
            SELECT 
                m.id,
                m.message_type,
                m.payload,
                m.created_at
            FROM messages m
            WHERE m.status = 'PENDING'
              AND m.created_at > NOW() - INTERVAL '1 hour'
              AND m.retry_count < 3
            ORDER BY m.priority DESC
            LIMIT 1000
            FOR UPDATE SKIP LOCKED
            """;
        System.out.println("SQL Query:\n" + sql);


        // ─────────────────────────────────────────────────────────────────
        // STRING INTERPOLATION with formatted()
        // ─────────────────────────────────────────────────────────────────

        String userId = "USR-12345";
        String userName = "Satya";
        int requestsPerMinute = 50000;

        String metrics = """
            {
              "userId": "%s",
              "userName": "%s",
              "metrics": {
                "requestsPerMinute": %d,
                "status": "active"
              }
            }
            """.formatted(userId, userName, requestsPerMinute);
        System.out.println("Formatted JSON:\n" + metrics);

        // ─────────────────────────────────────────────────────────────────
        // HTML TEMPLATE
        // ─────────────────────────────────────────────────────────────────

        String alertMessage = "High CPU usage on prod-server-01";
        String html = """
            <!DOCTYPE html>
            <html>
            <head><title>Alert</title></head>
            <body>
              <h1>System Alert</h1>
              <div class="alert alert-danger">
                <strong>Warning:</strong> %s
              </div>
            </body>
            </html>
            """.formatted(alertMessage);
        System.out.println("HTML Template (truncated):");
        System.out.println(html.lines().limit(5).collect(Collectors.joining("\n")) + "\n...");


        // ─────────────────────────────────────────────────────────────────
        // INDENTATION CONTROL - Position of closing """ matters!
        // ─────────────────────────────────────────────────────────────────

        // Closing """ at column 0 = no indentation removed
        String noIndent = """
Line 1
Line 2
""";

        // Closing """ aligned with content = incidental whitespace removed
        String withIndent = """
            Line 1
            Line 2
            """;

        System.out.println("Indentation control:");
        System.out.println("  No indent: [" + noIndent.replace("\n", "\\n") + "]");
        System.out.println("  With indent: [" + withIndent.replace("\n", "\\n") + "]");


        // ─────────────────────────────────────────────────────────────────
        // REAL-WORLD: Application Configuration
        // ─────────────────────────────────────────────────────────────────

        String yamlConfig = """
            spring:
              application:
                name: message-processor
              activemq:
                broker-url: tcp://localhost:61616
                pool:
                  enabled: true
                  max-connections: 50
              jpa:
                hibernate:
                  ddl-auto: validate
            """;
        System.out.println("YAML Config:\n" + yamlConfig);
    }
}
