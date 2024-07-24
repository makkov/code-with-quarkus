package org.acme.quarkus.second.kafka;

import io.smallrye.mutiny.Multi;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import java.time.Duration;

@ApplicationScoped
public class PongProducer {

    @Outgoing("pongs-out")
    public Multi<String> generate() {
        return Multi.createFrom().ticks().every(Duration.ofSeconds(5))
                .map(x -> "pong");
    }
}
