# Java 11-21 Features Reference

A comprehensive reference table of all Java features from Java 11 to Java 21.

## Complete Features Table

| Feature | Java Version | Description |
|---------|--------------|-------------|
| **var** | 10 (LTS: 11) | Local variable type inference - compiler infers type from initializer |
| **var in Lambda** | 11 | Use `var` in lambda parameters to enable annotations: `(@Nonnull var x) -> x` |
| **String.isBlank()** | 11 | Returns true for empty or whitespace-only strings |
| **String.lines()** | 11 | Returns `Stream<String>` of lines from multi-line string |
| **String.strip()** | 11 | Unicode-aware whitespace trimming (better than `trim()`) |
| **String.stripLeading()** | 11 | Remove leading whitespace only |
| **String.stripTrailing()** | 11 | Remove trailing whitespace only |
| **String.repeat(n)** | 11 | Repeat string n times: `"Ha".repeat(3)` → `"HaHaHa"` |
| **Collection.toArray(IntFunction)** | 11 | Type-safe array conversion: `list.toArray(String[]::new)` |
| **Files.readString()** | 11 | Read entire file as String |
| **Files.writeString()** | 11 | Write String to file |
| **HttpClient** | 11 | Modern HTTP client with async support (replaces HttpURLConnection) |
| **String.indent(n)** | 12 | Add/remove n spaces of indentation per line |
| **String.transform()** | 12 | Apply function to string: `str.transform(String::toUpperCase)` |
| **Collectors.teeing()** | 12 | Combine two collectors into one result |
| **Switch Expressions (Preview)** | 12 | Switch as expression with arrow syntax |
| **Text Blocks (Preview)** | 13 | Multi-line string literals with `"""` |
| **Switch Expressions (Finalized)** | 14 | Arrow syntax `->`, `yield` keyword, returns value |
| **Records (Preview)** | 14 | Immutable data carriers with auto-generated methods |
| **Pattern Matching instanceof (Preview)** | 14 | `if (obj instanceof String s)` - binding variable |
| **Helpful NullPointerExceptions** | 14 | NPE messages show exactly which variable was null |
| **Text Blocks (Finalized)** | 15 | Multi-line strings: `"""..."""` with `formatted()` |
| **Sealed Classes (Preview)** | 15 | Restrict which classes can extend/implement |
| **Records (Finalized)** | 16 | `record User(String name, int age) {}` - immutable DTOs |
| **Pattern Matching instanceof (Finalized)** | 16 | Type test + cast + variable in one expression |
| **Stream.toList()** | 16 | Shorthand for `.collect(Collectors.toList())` - returns unmodifiable list |
| **Sealed Classes (Finalized)** | 17 | `sealed class Shape permits Circle, Square {}` |
| **Pattern Matching for switch (Preview)** | 17 | Type patterns in switch: `case String s ->` |
| **UTF-8 by Default** | 18 | UTF-8 is default charset for Java APIs |
| **Simple Web Server** | 18 | `jwebserver` command for static file serving |
| **Code Snippets in JavaDoc** | 18 | `@snippet` tag for better code examples |
| **Record Patterns (Preview)** | 19 | Destructure records: `case Point(int x, int y) ->` |
| **Virtual Threads (Preview)** | 19 | Lightweight threads managed by JVM |
| **Structured Concurrency (Incubator)** | 19 | Manage multiple concurrent tasks as unit |
| **Record Patterns (Second Preview)** | 20 | Nested patterns: `case Rect(Point(var x, _), _) ->` |
| **Scoped Values (Incubator)** | 20 | Safer alternative to ThreadLocal for virtual threads |
| **Pattern Matching for switch (Finalized)** | 21 | Type patterns, guards (`when`), null handling |
| **Record Patterns (Finalized)** | 21 | Deep destructuring in instanceof and switch |
| **Virtual Threads (Finalized)** | 21 | `Thread.startVirtualThread()`, millions of threads |
| **Sequenced Collections** | 21 | `getFirst()`, `getLast()`, `addFirst()`, `reversed()` |
| **Unnamed Patterns & Variables** | 21 | Use `_` for intentionally unused: `case Point(int x, _) ->` |
| **Unnamed Classes (Preview)** | 21 | Implicit class for simple programs (main method only) |
| **String Templates (Preview)** | 21 | `STR."Hello \{name}"` - embedded expressions |
| **Scoped Values (Preview)** | 21 | Immutable, inheritable context for virtual threads |
| **Structured Concurrency (Preview)** | 21 | `StructuredTaskScope` for coordinated subtasks |

---

## Features by Java Version

### Java 11 (LTS - September 2018)
| Feature | Description |
|---------|-------------|
| var | Local variable type inference |
| var in Lambda | `(@Nonnull var x) -> x` |
| String.isBlank() | Check for empty/whitespace |
| String.lines() | Stream of lines |
| String.strip() | Unicode-aware trim |
| String.repeat(n) | Repeat n times |
| Files.readString() | Read file to String |
| Files.writeString() | Write String to file |
| HttpClient | Modern HTTP client |

### Java 12 (March 2019)
| Feature | Description |
|---------|-------------|
| String.indent(n) | Add/remove indentation |
| String.transform() | Functional string transformation |
| Collectors.teeing() | Combine two collectors |
| Switch Expressions (Preview) | Switch as expression |

### Java 13 (September 2019)
| Feature | Description |
|---------|-------------|
| Text Blocks (Preview) | Multi-line strings `"""` |

### Java 14 (March 2020)
| Feature | Description |
|---------|-------------|
| Switch Expressions (Finalized) | Arrow syntax, yield, returns value |
| Records (Preview) | Immutable data carriers |
| Pattern Matching instanceof (Preview) | Type pattern binding |
| Helpful NullPointerExceptions | Detailed NPE messages |

### Java 15 (September 2020)
| Feature | Description |
|---------|-------------|
| Text Blocks (Finalized) | `"""` with formatted() |
| Sealed Classes (Preview) | Restricted inheritance |

### Java 16 (March 2021)
| Feature | Description |
|---------|-------------|
| Records (Finalized) | `record User(String name) {}` |
| Pattern Matching instanceof (Finalized) | `if (obj instanceof String s)` |
| Stream.toList() | Unmodifiable list from stream |

### Java 17 (LTS - September 2021)
| Feature | Description |
|---------|-------------|
| Sealed Classes (Finalized) | `sealed class X permits A, B {}` |
| Pattern Matching for switch (Preview) | Type patterns in switch |

### Java 18 (March 2022)
| Feature | Description |
|---------|-------------|
| UTF-8 by Default | Default charset is UTF-8 |
| Simple Web Server | `jwebserver` command |
| Code Snippets in JavaDoc | `@snippet` tag |

### Java 19 (September 2022)
| Feature | Description |
|---------|-------------|
| Record Patterns (Preview) | Destructure records in patterns |
| Virtual Threads (Preview) | Lightweight JVM-managed threads |
| Structured Concurrency (Incubator) | Coordinated task management |

### Java 20 (March 2023)
| Feature | Description |
|---------|-------------|
| Record Patterns (Second Preview) | Nested destructuring |
| Scoped Values (Incubator) | ThreadLocal alternative |

### Java 21 (LTS - September 2023)
| Feature | Description |
|---------|-------------|
| Pattern Matching for switch (Finalized) | Type patterns, guards, null handling |
| Record Patterns (Finalized) | Deep destructuring |
| Virtual Threads (Finalized) | Production-ready lightweight threads |
| Sequenced Collections | getFirst(), getLast(), reversed() |
| Unnamed Patterns & Variables | `_` for unused variables |
| Unnamed Classes (Preview) | Implicit class for simple programs |
| String Templates (Preview) | `STR."Hello \{name}"` |
| Scoped Values (Preview) | Context for virtual threads |
| Structured Concurrency (Preview) | StructuredTaskScope |

---

## Quick Syntax Reference

### var (Java 11)
```java
// Type inferred from right side
var list = new ArrayList<String>();
var map = new HashMap<String, List<Integer>>();

// In for loops
for (var item : collection) { }
for (var i = 0; i < 10; i++) { }

// In lambda (allows annotations)
Function<String, String> f = (@Nonnull var s) -> s.toUpperCase();
```

### String Methods (Java 11-12)
```java
"   ".isBlank();              // true
"a\nb\nc".lines().toList();   // ["a", "b", "c"]
"  hello  ".strip();          // "hello"
"Ha".repeat(3);               // "HaHaHa"
"hello".indent(4);            // "    hello"
"hello".transform(String::toUpperCase);  // "HELLO"
```

### Text Blocks (Java 15)
```java
String json = """
    {
      "name": "%s",
      "email": "%s"
    }
    """.formatted(name, email);

String sql = """
    SELECT *
    FROM users
    WHERE status = 'ACTIVE'
    """;
```

### Records (Java 16)
```java
// Basic record
record User(String name, String email) {}

// With validation (compact constructor)
record User(String name, String email) {
    public User {
        Objects.requireNonNull(name, "name required");
        email = email.toLowerCase();  // normalize
    }
}

// With custom methods
record Point(int x, int y) {
    public double distance() {
        return Math.sqrt(x * x + y * y);
    }
}

// Usage
var user = new User("John", "john@example.com");
String name = user.name();  // accessor (not getName!)
```

### Sealed Classes (Java 17)
```java
// Sealed interface
sealed interface Result<T> permits Success, Failure {}
record Success<T>(T value) implements Result<T> {}
record Failure<T>(Exception error) implements Result<T> {}

// Sealed class
sealed abstract class Shape permits Circle, Rectangle, Triangle {}
final class Circle extends Shape { }
final class Rectangle extends Shape { }
non-sealed class Triangle extends Shape { }  // open for extension
```

### Pattern Matching instanceof (Java 16)
```java
// Old way
if (obj instanceof String) {
    String s = (String) obj;
    System.out.println(s.length());
}

// New way
if (obj instanceof String s) {
    System.out.println(s.length());
}

// With conditions
if (obj instanceof String s && s.length() > 5) {
    System.out.println("Long string: " + s);
}
```

### Switch Expressions (Java 14)
```java
// Arrow syntax - no fall-through!
String result = switch (day) {
    case MONDAY, TUESDAY, WEDNESDAY -> "Early week";
    case THURSDAY, FRIDAY -> "Late week";
    case SATURDAY, SUNDAY -> "Weekend";
};

// With yield for blocks
int value = switch (status) {
    case "HIGH" -> 1;
    case "MEDIUM" -> {
        log("Processing medium");
        yield 2;
    }
    default -> 3;
};
```

### Pattern Matching for switch (Java 21)
```java
// Type patterns
String describe(Object obj) {
    return switch (obj) {
        case null -> "null";
        case String s -> "String: " + s;
        case Integer i -> "Integer: " + i;
        case List<?> list -> "List of " + list.size();
        default -> "Unknown";
    };
}

// Guarded patterns (when clause)
String categorize(Integer n) {
    return switch (n) {
        case Integer i when i < 0 -> "negative";
        case Integer i when i == 0 -> "zero";
        case Integer i when i < 100 -> "small";
        case Integer i -> "large";
    };
}
```

### Record Patterns (Java 21)
```java
// Basic destructuring
if (obj instanceof Point(int x, int y)) {
    System.out.println(x + ", " + y);
}

// In switch
String describe(Object obj) {
    return switch (obj) {
        case Point(int x, int y) -> "Point at " + x + "," + y;
        case Rectangle(Point(int x, int y), int w, int h) -> 
            "Rectangle at " + x + "," + y;
        default -> "Unknown";
    };
}

// Nested patterns
if (response instanceof ApiResponse(
        Status(int code, String msg),
        Payload(User(String name, _), _)
    ) && code == 200) {
    System.out.println("User: " + name);
}
```

### Virtual Threads (Java 21)
```java
// Create single virtual thread
Thread.startVirtualThread(() -> doWork());

// Thread builder
Thread vThread = Thread.ofVirtual()
    .name("my-thread")
    .start(() -> doWork());

// Executor for massive concurrency
try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
    for (int i = 0; i < 100_000; i++) {
        executor.submit(() -> {
            Thread.sleep(100);  // I/O simulation
            return "done";
        });
    }
}

// Check if virtual
Thread.currentThread().isVirtual();

// Spring Boot integration
// application.properties
spring.threads.virtual.enabled=true
```

### Sequenced Collections (Java 21)
```java
List<String> list = new ArrayList<>(List.of("a", "b", "c"));

list.getFirst();      // "a"
list.getLast();       // "c"
list.addFirst("z");   // ["z", "a", "b", "c"]
list.addLast("d");    // ["z", "a", "b", "c", "d"]
list.removeFirst();   // removes and returns "z"
list.removeLast();    // removes and returns "d"
list.reversed();      // reversed VIEW (not copy!)

// Works on LinkedHashSet, LinkedHashMap, TreeSet, TreeMap
SequencedSet<String> set = new LinkedHashSet<>();
SequencedMap<String, Integer> map = new LinkedHashMap<>();
map.firstEntry();
map.lastEntry();
map.pollFirstEntry();
```

### Unnamed Patterns & Variables (Java 21 - Preview)
```java
// Unused exception
try {
    Integer.parseInt("abc");
} catch (NumberFormatException _) {
    System.out.println("Invalid number");
}

// Unused loop variable
int count = 0;
for (var _ : items) {
    count++;
}

// Unused pattern components
case Point(int x, _) -> "x=" + x;
case User(var name, _, _) -> "name=" + name;
```

---

## LTS (Long-Term Support) Versions

| Version | Release Date | Support Until |
|---------|--------------|---------------|
| Java 11 | Sept 2018 | Sept 2026+ |
| Java 17 | Sept 2021 | Sept 2029+ |
| Java 21 | Sept 2023 | Sept 2031+ |

---

## Migration Path Summary

| From | To | Key Changes |
|------|-----|-------------|
| Java 11 → 17 | Records, Sealed Classes, Pattern Matching instanceof |
| Java 17 → 21 | Virtual Threads, Pattern Matching switch, Record Patterns, Sequenced Collections |

---
