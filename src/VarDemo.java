import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VarDemo {

    void main() {
        // Old way
        String oldMessage = "hello world";
        ArrayList<String> oldList = new ArrayList<>();
        Map<String, List<Map<String, Object>>> oldComplex = new HashMap<>();

        //New way
        var newMessage = "hello world";
        var newList = new ArrayList<String>();
        var newComplex = new HashMap<String, List<Map<String, Object>>>();

    /* ❌ Cannot use var for:
        var x;                    // No initializer
        var y = null;             // Cannot infer type from null
        var z = {1, 2, 3};        // Array initializer without type
        var lambda = (x) -> x;    // Lambda without target type


        ✅ USE var when:
       • Type is obvious from right side:
         var list = new ArrayList<String>();
       • Complex generic types:
         var map = new HashMap<String, List<Integer>>();
       • For-each loops with obvious types:
         for (var item : items)
       • Try-with-resources:
         try (var reader = new FileReader(...)) { }

    ❌ AVOID var when:
       • Type isn't clear:
         var result = process();  // What type?
       • Primitive literals:
         var count = 0;  // Is it int? long?
       • Method return types make it unclear:
         var data = service.fetch();

    💡 RULE:
       var should make code MORE readable, not less!
    */
    }
}
