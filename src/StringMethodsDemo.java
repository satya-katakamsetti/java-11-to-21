public class StringMethodsDemo {

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
