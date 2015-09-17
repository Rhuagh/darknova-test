package se.darknova.service;

import com.google.common.util.concurrent.ServiceManager;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/**
 * @author seamonr@gmail.com
 */
@Singleton
@Slf4j
public class GuavaRun {
    private final Provider<ServiceManager> serviceManager;

    @Inject
    public GuavaRun(Provider<ServiceManager> serviceManager) {
        this.serviceManager = serviceManager;
        Runtime.getRuntime().addShutdownHook(new Thread(this::stop));
    }

    public void start() {
        log.info("Starting all services");
        serviceManager.get().startAsync().awaitHealthy();
    }

    public void stop() {
        log.info("Stopping all services");
        serviceManager.get().stopAsync().awaitStopped();
    }
}
