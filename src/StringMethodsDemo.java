public class StringMethodsDemo {
/*
     * Java has introduced several useful methods to the `String` class in recent
     * versions (Java 11, 12, and 15), making common text manipulations more
     * convenient and expressive.
     *
     * Key points:
     *  - `isBlank()` (Java 11): Checks if a string is empty or contains only whitespace.
     *    A more intuitive check than `trim().isEmpty()`.
     *  - `lines()` (Java 11): Returns a `Stream<String>` of lines from a multi-line string.
     *  - `repeat(n)` (Java 11): Repeats the string `n` times.
     *  - `strip()`, `stripLeading()`, `stripTrailing()` (Java 11): Unicode-aware
     *    alternatives to `trim()`.
     *  - `indent(n)` (Java 12): Adds or removes leading whitespace from each line.
     *  - `transform(fn)` (Java 12): Applies a function to the string, enabling
     *    fluent, chainable transformations.
     *
     * Example:
     *   var result = "  hello_world  "
     *           .transform(String::strip)
     *           .transform(String::toUpperCase)
     *           .transform(s -> s.replace("_", " "));
     *
     * Purpose:
     *   To provide modern, convenient, and often functionally-oriented methods for
     *   common string operations, reducing the need for external libraries like Apache Commons Lang.
     */

    public static void main(String[] args) {
        var emptyString = "";
        var blankString = "   ";
        var textString = "hello";

        // ─────────────────────────────────────────────────────────────────
        // isBlank() - Better than isEmpty() for whitespace (Java 11)
        // ─────────────────────────────────────────────────────────────────

        System.out.println("isEmpty vs isBlank:");
        System.out.println("  ''.isEmpty() = " + emptyString.isEmpty());
        System.out.println("  '   '.isEmpty() = " + blankString.isEmpty());
        System.out.println("  '   '.isBlank() = " + blankString.isBlank());
        System.out.println("  'hello'.isBlank() = " + textString.isBlank());

        // ─────────────────────────────────────────────────────────────────
        // lines() - Stream of lines (Java 11)
        // ─────────────────────────────────────────────────────────────────

        var multiline = "Line1 \nLine2 \nLine3";
        multiline.lines().forEach(System.out::println);

        // ─────────────────────────────────────────────────────────────────
        // repeat() - Repeat string N times (Java 11)
        // ─────────────────────────────────────────────────────────────────

        var name = "rama";
        System.out.println(name.repeat(4));

        // ─────────────────────────────────────────────────────────────────
        // indent() - Add/remove indentation (Java 12)
        // ─────────────────────────────────────────────────────────────────

        var text = "Hello\nWorld";
        System.out.println("indent(4)\n"+ text.indent(4));

        // ─────────────────────────────────────────────────────────────────
        // transform() - Functional transformation (Java 12)
        // ─────────────────────────────────────────────────────────────────

        var result = "hello_world  "
                .transform(String::strip)
                .transform(String::toUpperCase)
                .transform(s -> s.replace("_", ""));
        System.out.println(result);

        // ─────────────────────────────────────────────────────────────────
        // stripIndent() & translateEscapes() (Java 15) - For text blocks
        // ─────────────────────────────────────────────────────────────────

        var indentedText = """
                <html>
                    <body>
                        <h1>Hello</h1>
                    </body>
                </html>
                """;
        System.out.println("stripIndent(): \n" + indentedText.stripIndent());
        var escaped = "Hello\\nWorld\\t!";
        System.out.println("translateEscapes(): " + escaped.translateEscapes());
    }
}
