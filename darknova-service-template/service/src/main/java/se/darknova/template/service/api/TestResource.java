package se.darknova.template.service.api;

import com.netflix.servo.annotations.DataSourceType;
import com.netflix.servo.annotations.Monitor;
import lombok.RequiredArgsConstructor;
import se.darknova.monitor.metrics.MonitorClass;
import se.darknova.template.service.TestService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author seamonr@gmail.com
 */
@Path("/test")
@RequiredArgsConstructor(onConstructor = @__(@Inject))
@MonitorClass
@Singleton
public class TestResource {

    @Monitor(name = "TestResource.numRequests",
             type = DataSourceType.COUNTER,
             description = "Number of requests")
    private AtomicInteger numRequests = new AtomicInteger(0);

    private final TestService service;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String get() {
        numRequests.incrementAndGet();
        return service.get();
    }
}
