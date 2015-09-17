package se.darknova.discovery;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import lombok.EqualsAndHashCode;

/**
 * @author seamonr@gmail.com
 */
@EqualsAndHashCode(callSuper=false, of={})
public class DiscoveryModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Discovery.class).toProvider(DiscoveryProvider.class);
    }

}
