package se.darknova.monitor.metrics.graphite;

import com.netflix.servo.publish.graphite.GraphiteMetricObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import se.darknova.monitor.metrics.ServoConfig;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/**
 * @author simon
 */
@RequiredArgsConstructor(onConstructor = @__(@Inject))
@Slf4j
public class GraphiteObserverProvider implements Provider<GraphiteMetricObserver> {

    private final ServoConfig config;

    @Override
    @Singleton
    public GraphiteMetricObserver get() {
        if(config.isGraphiteEnabled()) {
            return new GraphiteMetricObserver(config.getGraphiteServerPrefix(),
                                              config.getGraphiteServerAddress());
        }
        return null;
    }
}
