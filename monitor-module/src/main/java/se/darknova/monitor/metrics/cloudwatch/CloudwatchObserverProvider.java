package se.darknova.monitor.metrics.cloudwatch;

import com.netflix.servo.publish.cloudwatch.CloudWatchMetricObserver;
import lombok.RequiredArgsConstructor;
import se.darknova.monitor.metrics.ServoConfig;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * @author simon
 */
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class CloudwatchObserverProvider implements Provider<CloudWatchMetricObserver> {

    private final ServoConfig config;

    @Override
    public CloudWatchMetricObserver get() {
        if (config.isCloudwatchEnabled()) {
            return new CloudWatchMetricObserver("servo-cloudwatch", config.getCloudwatchNamespace(),
                                                new AWSConfigCredentials(config));
        }
        return null;
    }
}
