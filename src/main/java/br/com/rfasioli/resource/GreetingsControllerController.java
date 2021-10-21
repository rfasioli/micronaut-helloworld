package br.com.rfasioli.resource;

import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;

@Controller("/greetings")
public class GreetingsControllerController implements GreetingsControllerRoutes {

    @Value("${greeting.message}")
    private String greeting;

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }

    @Override
    public HttpResponse<String> greeting(String name) {
        return HttpResponse.ok(greeting + ", " + name + "!");
    }
}