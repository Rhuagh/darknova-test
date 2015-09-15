package se.darknova.template.client.discovery;

import com.google.inject.AbstractModule;

/**
 * @author seamonr@gmail.com
 */
public class DiscoveryModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Discovery.class).to(DNSDiscovery.class).asEagerSingleton();
    }
}
