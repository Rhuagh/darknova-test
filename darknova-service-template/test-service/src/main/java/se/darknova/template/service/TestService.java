package se.darknova.template.service;

import com.netflix.servo.annotations.DataSourceType;
import com.netflix.servo.annotations.Monitor;
import lombok.RequiredArgsConstructor;
import se.darknova.monitor.metrics.MonitorClass;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author seamonr@gmail.com
 */
@Singleton
@RequiredArgsConstructor(onConstructor = @__(@Inject))
@MonitorClass
public class TestService {

    @Monitor(name = "TestService.numRequests",
             type = DataSourceType.COUNTER,
             description = "Number of requests")
    private AtomicInteger numRequests = new AtomicInteger(0);

    private final TestConfig config;

    public String get() {
        numRequests.incrementAndGet();
        return config.getMessage() + " (at " + this + ")";
    }
}
