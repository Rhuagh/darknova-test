package se.darknova.client;

import com.google.inject.AbstractModule;
import se.darknova.discovery.DiscoveryModule;

/**
 * @author simon
 */
public class DiscoveryClientModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new DiscoveryModule());
        bind(DiscoveryTargetClientFactory.class).asEagerSingleton();
    }
}
