package se.darknova.template.service.api;

import se.darknova.template.service.TestService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author seamonr@gmail.com
 */
@Path("/test")
public class TestResource {

    private final TestService service;

    @Inject
    public TestResource(TestService service) {
        this.service = service;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String get() {
        return service.get();
    }
}
