package se.darknova.service;

import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.Service;
import com.google.common.util.concurrent.ServiceManager;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

/**
 * @author seamonr@gmail.com
 */
// TODO: logging
@Singleton
@Slf4j
public class GuavaServiceRepository {

    private class ServiceManagerListener extends ServiceManager.Listener {

        @Override
        public void healthy() {
            log.info("All services are healthy");
        }

        @Override
        public void stopped() {
            log.info("All services are stopped");
        }

        @Override
        public void failure(Service service) {
            log.error("Service failure in {}: {}", service.toString(), service.failureCause());
            System.exit(1);
        }
    }

    private final List<Service> services = new ArrayList<>();
    private final List<Service.Listener> serviceListeners = new ArrayList<>();
    private final List<ServiceManager.Listener> serviceManagerListeners = new ArrayList<>();

    private boolean started = false;
    private ServiceManager serviceManager;

    public void add(Service service) {
        Preconditions.checkState(!started, "Already started");
        services.add(service);
    }

    public void add(Service.Listener listener) {
        Preconditions.checkState(!started, "Already started");
        serviceListeners.add(listener);
    }

    public void add(ServiceManager.Listener listener) {
        Preconditions.checkState(!started, "Already started");
        serviceManagerListeners.add(listener);
    }

    public boolean isHealthy() {
        return started && serviceManager.isHealthy();
    }

    ServiceManager createServiceManager() {
        Preconditions.checkState(!started, "Already started");
        started = true;
        serviceManager = new ServiceManager(services);

        serviceManager.addListener(new ServiceManagerListener(), MoreExecutors.directExecutor());
        for (ServiceManager.Listener listener : serviceManagerListeners) {
            serviceManager.addListener(listener, MoreExecutors.directExecutor());
        }

        for (Service service : services) {
            for (Service.Listener listener : serviceListeners) {
                service.addListener(listener, MoreExecutors.directExecutor());
            }
        }

        return serviceManager;
    }

}
