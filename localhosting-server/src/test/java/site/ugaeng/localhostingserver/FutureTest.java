package site.ugaeng.localhostingserver;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.*;

public class FutureTest {

    Random random = new Random();

    @Test
    void do_async() throws ExecutionException, InterruptedException, TimeoutException, IOException {

        CompletableFuture<Double> futurePrice = new CompletableFuture<>();

        
    }

    @Test
    void do_sync() {
        // given

        // when

        // then

    }

    double getPrice(String product) {
        return calculatePrice(product);
    }

    private double calculatePrice(String product) {
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static class Processor implements Callable<String> {

        private final Socket connection;

        public Processor(Socket connection) {
            this.connection = connection;
        }

        @Override
        public String call() throws Exception {
            return null;
        }
    }
}
