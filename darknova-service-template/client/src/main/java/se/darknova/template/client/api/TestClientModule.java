package se.darknova.template.client.api;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import se.darknova.client.DiscoveryClientModule;
import se.darknova.client.DiscoveryTargetClientFactory;
import se.darknova.template.api.Test;

/**
 * @author simon
 */
public class TestClientModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new DiscoveryClientModule());
    }

    @Provides
    public Test getTestClient(DiscoveryTargetClientFactory factory) {
        return factory.getDiscoveryProxy(Test.SERVICE_NAME, Test.class);
    }
}
