package se.darknova.service;

import com.google.common.util.concurrent.ServiceManager;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/**
 * @author seamonr@gmail.com
 */
@Singleton
public class GuavaRun {
    private final Provider<ServiceManager> serviceManager;

    @Inject
    public GuavaRun(Provider<ServiceManager> serviceManager) {
        this.serviceManager = serviceManager;
        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {
                GuavaRun.this.stop();
            }
        });
    }

    public void start() {
        serviceManager.get().startAsync().awaitHealthy();
    }

    public void stop() {
        serviceManager.get().stopAsync().awaitStopped();
    }
}
