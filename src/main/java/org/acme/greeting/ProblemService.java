package org.acme.greeting;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class ProblemService {

    private final Random random = new Random();
    private final AtomicLong counter = new AtomicLong(0);


    public String retry() {
        Log.infof("Try to retry\n");
        throw new RuntimeException("Problem with retry");
    }
    public String timeout() {
        int timeout = random.nextInt(1000);
        Log.infof("Will imitate timeout: %s\n", timeout);
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Hello from service!";
    }

    @Fallback(fallbackMethod = "fallbackRecover")
    public String fallback() {
        boolean isWithProblem = random.nextBoolean();
        if (isWithProblem) {
            Log.infof("We have a some problem!");
            throw new RuntimeException("Some problem");
        }

        return "Hello from fallback!";
    }

    public String fallbackRecover() {
        return "Hello from fallbackRecover!";
    }

    @CircuitBreaker(requestVolumeThreshold = 4)
    public String circuitBreaker() {
        problemImitator();
        return "Hello from circuitBreaker";
    }

    private void problemImitator() {
        // introduce some artificial failures
        final Long invocationNumber = counter.getAndIncrement();
        Log.infof("Counter: %s, number: %s\n", counter, invocationNumber);
        if (invocationNumber % 4 > 1) { // alternate 2 successful and 2 failing invocations
            throw new RuntimeException("Service failed.");
        }
    }

    public String rateLimit() {
        return "Hello from RateLimit!";
    }

}
