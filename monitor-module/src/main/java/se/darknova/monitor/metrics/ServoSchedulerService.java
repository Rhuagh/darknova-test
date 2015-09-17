package se.darknova.monitor.metrics;

import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.AbstractIdleService;
import com.netflix.servo.publish.AsyncMetricObserver;
import com.netflix.servo.publish.BasicMetricFilter;
import com.netflix.servo.publish.CounterToRateMetricTransform;
import com.netflix.servo.publish.JvmMetricPoller;
import com.netflix.servo.publish.MetricObserver;
import com.netflix.servo.publish.MetricPoller;
import com.netflix.servo.publish.MonitorRegistryMetricPoller;
import com.netflix.servo.publish.PollRunnable;
import com.netflix.servo.publish.PollScheduler;
import com.netflix.servo.publish.cloudwatch.CloudWatchMetricObserver;
import com.netflix.servo.publish.graphite.GraphiteMetricObserver;
import lombok.extern.slf4j.Slf4j;
import se.darknova.service.GuavaServiceRepository;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author simon
 */
@Singleton
@Slf4j
public class ServoSchedulerService extends AbstractIdleService {

    private final ServoConfig config;
    private final Provider<GraphiteMetricObserver> graphiteMetricObserverProvider;
    private final Provider<CloudWatchMetricObserver> cloudWatchMetricObserverProvider;

    private boolean started = false;
    private PollScheduler scheduler;

    @Inject
    public ServoSchedulerService(ServoConfig config,
                                 Provider<GraphiteMetricObserver> graphiteMetricObserverProvider,
                                 Provider<CloudWatchMetricObserver> cloudWatchMetricObserverProvider,
                                 GuavaServiceRepository serviceRepository) {
        this.config = config;
        this.graphiteMetricObserverProvider = graphiteMetricObserverProvider;
        this.cloudWatchMetricObserverProvider = cloudWatchMetricObserverProvider;
        serviceRepository.add(this);
    }

    @Override
    protected void startUp() throws Exception {
        Preconditions.checkState(!started, "Service already started");
        log.info("Starting servo scheduler");
        scheduler = PollScheduler.getInstance();
        scheduler.start();
        List<MetricObserver> observers = getObservers();
        if (!observers.isEmpty()) {
            addToScheduler(scheduler, new MonitorRegistryMetricPoller(), observers);
            if(config.isJvmPollerEnabled()) {
                addToScheduler(scheduler, new JvmMetricPoller(), observers);
            }
        }
        started = true;
    }

    @Override
    protected void shutDown() throws Exception {
        Preconditions.checkState(started, "Service not started");
        scheduler.stop();
        started = false;
    }

    private List<MetricObserver> getObservers() {
        List<MetricObserver> observers = new ArrayList<>();
        if(config.isGraphiteEnabled()) {
            GraphiteMetricObserver observer = graphiteMetricObserverProvider.get();
            if(observer != null) {
                observers.add(rateTransform(async("graphite", observer)));
            }
        }
        if(config.isCloudwatchEnabled()) {
            CloudWatchMetricObserver observer = cloudWatchMetricObserverProvider.get();
            if(observer != null) {
                observers.add(rateTransform(async("cloudwatch", observer)));
            }
        }
        return observers;
    }

    private void addToScheduler(PollScheduler scheduler,
                                MetricPoller poller,
                                List<MetricObserver> observers) {
        scheduler.addPoller(new PollRunnable(poller, BasicMetricFilter.MATCH_ALL, true, observers),
                            config.getPollInterval(),
                            TimeUnit.SECONDS);
    }

    private MetricObserver async(String name, MetricObserver observer) {
        return new AsyncMetricObserver(name,
                                       observer,
                                       config.getAsynchronousQueueSize(),
                                       config.getPollInterval() * 2000);
    }

    private MetricObserver rateTransform(MetricObserver observer) {
        return new CounterToRateMetricTransform(observer,
                                                config.getPollInterval() * 2,
                                                TimeUnit.SECONDS);
    }
}
