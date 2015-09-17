package se.darknova.monitor.health;

import lombok.RequiredArgsConstructor;
import se.darknova.service.GuavaServiceRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * @author simon
 */
@Path("/health")
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class HealthResource {

    private final GuavaServiceRepository serviceRepository;

    @GET
    public Response get() {
        if (serviceRepository.isHealthy())
            return Response.ok().build();
        else
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
    }
}
