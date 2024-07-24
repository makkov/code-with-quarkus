package org.acme.quarkus.second.kafka;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class PingConsumer {

    @Incoming("pings")
    public void consume(String msg) {

        Log.infof("Message from pings topic: %s", msg);
    }
}
