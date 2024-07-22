package org.acme;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class GreetingResourceTest {

    @Test
    void testHelloEndpoint() {
        String greetMsg = "Привет from Service!";
        GreetingService greetingService = Mockito.mock(GreetingService.class);
        Mockito.when(greetingService.greet()).thenReturn(greetMsg);

        given()
                .when().get("/hello")
                .then()
                .statusCode(200)
                .body(is(greetMsg));
    }
}
