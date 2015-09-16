package se.darknova.config.consul;

import com.google.inject.AbstractModule;
import com.google.inject.util.Modules;
import com.netflix.archaius.Config;
import com.netflix.archaius.guice.ArchaiusModule;
import com.netflix.archaius.inject.ApplicationOverrideLayer;

import javax.inject.Singleton;

/**
 * @author seamonr@gmail.com
 */
public class ConsulConfigModule extends AbstractModule {

    @Override
    protected void configure() {
        install(Modules.override(new ArchaiusModule()).with(new AbstractModule() {
            @Override
            protected void configure() {
                bind(Config.class).annotatedWith(ApplicationOverrideLayer.class).toProvider(
                    ConsulConfigProvider.class).in(Singleton.class);
            }
        }));
    }
}
