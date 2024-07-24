package org.acme.greeting;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingService {

    public String greet() {
        return "Привет from Service!";
    }
}
