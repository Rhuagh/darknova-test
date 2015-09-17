package se.darknova.monitor.health;

import com.google.inject.AbstractModule;

/**
 * @author simon
 */
public class HealthModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(HealthResource.class);
    }
}
