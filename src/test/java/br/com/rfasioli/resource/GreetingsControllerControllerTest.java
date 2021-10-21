package br.com.rfasioli.resource;
import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import io.micronaut.http.client.annotation.*;
import jakarta.inject.Inject;
import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class GreetingsControllerControllerTest {

    @Inject
    EmbeddedServer embeddedServer;

    @Inject
    @Client("/greetings")
    GreetingsControllerRoutes client;

    @Value("${greeting.message}")
    String greetingMessage;

    @Test
    void testIndex() throws Exception {
        try(HttpClient client = embeddedServer.getApplicationContext().createBean(HttpClient.class, embeddedServer.getURL())) {
            assertEquals(HttpStatus.OK, client.toBlocking().exchange("/greetings").status());
        }
    }

    @Test
    void greetingsByNameTest() {
        final var name = "John";
        final var expected = String.format("%s, %s!", greetingMessage, name);

        HttpResponse<String> response = client.greeting(name);

        final var responseText = response.body();

        assertEquals(expected, responseText);
    }

}
