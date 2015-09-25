package se.darknova.service;

import com.google.common.util.concurrent.ServiceManager;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * @author simon
 */
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class ServiceManagerProvider implements Provider<ServiceManager> {

    private final GuavaServiceRepository repository;

    @Override
    public ServiceManager get() {
        return repository.createServiceManager();
    }
}
