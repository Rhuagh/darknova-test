package se.darknova.monitor.metrics;

import com.google.inject.AbstractModule;
import com.netflix.servo.publish.cloudwatch.CloudWatchMetricObserver;
import com.netflix.servo.publish.graphite.GraphiteMetricObserver;
import lombok.EqualsAndHashCode;
import se.darknova.monitor.metrics.cloudwatch.CloudwatchObserverProvider;
import se.darknova.monitor.metrics.graphite.GraphiteObserverProvider;

/**
 * @author simon
 */
@EqualsAndHashCode(callSuper = false, of = {})
public class ServoPublishingModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new ServoMetricsModule());
        bind(CloudWatchMetricObserver.class).toProvider(CloudwatchObserverProvider.class);
        bind(GraphiteMetricObserver.class).toProvider(GraphiteObserverProvider.class);
        bind(ServoSchedulerService.class).asEagerSingleton();
    }
}
