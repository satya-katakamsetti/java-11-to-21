import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class VirtualThreadsDemo {

    public static void main(String[] args) throws Exception {
        VirtualThreadsDemo demo = new VirtualThreadsDemo();
        demo.basiVirtualThread();
        demo.virtualThreadExecutor();
    }

    void basiVirtualThread() throws Exception {
        System.out.println("1. Basic Virtual Thread Creation: ");

        //Method 1: Thread.ofVirtual()

        Thread vThread = Thread.ofVirtual()
                .name("my-virtual-thread")
                .start(() -> {
                    System.out.println(" Running on: "+ Thread.currentThread());
                    System.out.println("  Is virtual: " + Thread.currentThread().isVirtual());
                });
        vThread.join();

        //Method 2: Thread.startVirtualThread()

        Thread quick = Thread.startVirtualThread( () ->
                System.out.println(" Quick virutal thread started!")
        );

        quick.join();

        // Compare with platform thread

        Thread platform = Thread.ofPlatform()
                .name("platform-tread")
                .start(() -> System.out.println(" Platformthread, isVirtual: " +
                        Thread.currentThread().isVirtual()));

        platform.join();
    }

    void virtualThreadExecutor() throws Exception {
        System.out.println("\n2. Virtual Thread Executor");

        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            List<Future<String>> futures  = new ArrayList<>();

            for (var i=0 ; i < 5; i++) {
                final  var taskId = i;
                Future<String> future = executorService.submit( () -> {
                    Thread.sleep(100);
                    return "Task " + taskId + " on " + Thread.currentThread().getName();
                });
                futures.add(future);

                for (Future<String> f : futures) {
                    System.out.println("  " + f.get());
                }
            }
        }
    }

    void realWorldSimulation() throws Exception {
        System.out.println("\n3. Real-World: HTTP Request Simulation (Your 50K RPM scenario!)");

        int requestCount = 1000;

        record HttpRequest(int id, String path) {}
        record HttpResponse(int requestId, int status, String body) {}

        System.out.println("  Processing " + requestCount + " concurrent requests...");
        Instant start = Instant.now();

        List<Future<HttpResponse>> responses = new ArrayList<>();

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < requestCount; i++) {
                final var request = new HttpRequest(i, "/api/messages/" + i);

                Future<HttpResponse> future = executor.submit(() -> {
                    // Simulate request processing:
                    Thread.sleep(1);   // Parse request
                    Thread.sleep(10);  // Database query
                    Thread.sleep(2);   // Business logic
                    return new HttpResponse(request.id(), 200, "OK");
                });
                responses.add(future);
            }

            int successCount = 0;
            for (var f : responses) {
                if (f.get().status() == 200) successCount++;
            }

            Duration duration = Duration.between(start, Instant.now());
            System.out.printf("  ✅ Processed %d/%d requests in %d ms%n",
                    successCount, requestCount, duration.toMillis());
            System.out.printf("  📊 Throughput: %,d requests/second%n",
                    requestCount * 1000 / Math.max(1, duration.toMillis()));
        }
    }
}
