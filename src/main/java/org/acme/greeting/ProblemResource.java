package org.acme.greeting;

import io.smallrye.faulttolerance.api.RateLimit;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

@Path("/problems")
public class ProblemResource {

    final ProblemService problemService;

    public ProblemResource(ProblemService problemService) {
        this.problemService = problemService;
    }

    @GET
    @Path("/retry")
    @Retry(maxRetries = 4)
    @Produces(MediaType.TEXT_PLAIN)
    public String retry() {
        return problemService.retry();
    }

    @GET
    @Path("/timeout")
    @Timeout(value = 500)
    @Produces(MediaType.TEXT_PLAIN)
    public String timeout() {
        return problemService.timeout();
    }

    @GET
    @Path("/fallback")
    @Timeout(value = 500)
    @Produces(MediaType.TEXT_PLAIN)
    public String fallback() {
        return problemService.fallback();
    }

    @GET
    @Path("/circuit-breaker")
    @Produces(MediaType.TEXT_PLAIN)
    public String circuitBreaker() {
        return problemService.circuitBreaker();
    }

    @GET
    @Path("/rate-limit")
    @RateLimit(value = 2, window = 10)
    @Produces(MediaType.TEXT_PLAIN)
    public String rateLimit() {
        return problemService.rateLimit();
    }

}
