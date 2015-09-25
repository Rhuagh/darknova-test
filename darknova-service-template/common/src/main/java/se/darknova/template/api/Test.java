package se.darknova.template.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author simon
 */
@Path("/test")
public interface Test {

    String SERVICE_NAME = "darknova-test";

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    String get();
}
