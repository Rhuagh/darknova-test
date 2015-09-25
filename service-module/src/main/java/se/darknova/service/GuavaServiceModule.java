package se.darknova.service;

import com.google.common.util.concurrent.ServiceManager;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import lombok.EqualsAndHashCode;

/**
 * @author seamonr@gmail.com
 */
@EqualsAndHashCode(callSuper=false, of={})
public class GuavaServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(GuavaRun.class);
        bind(ServiceManager.class).toProvider(ServiceManagerProvider.class).in(Singleton.class);
    }

}
