package org.acme;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProblemService {

    public String retry() {
        Log.infof("Try to retry\n");
        throw new RuntimeException("Problem with retry");
    }
}
