package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.faulttolerance.Retry;

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
}
