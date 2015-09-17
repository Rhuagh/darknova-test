package se.darknova.monitor;

import com.google.inject.AbstractModule;
import se.darknova.monitor.health.HealthModule;
import se.darknova.monitor.metrics.ServoMetricsModule;
import se.darknova.monitor.metrics.ServoPublishingModule;

/**
 * @author simon
 */
public class FullMonitorModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new ServoMetricsModule());
        install(new ServoPublishingModule());
        install(new HealthModule());
    }
}
