package se.darknova.template.service.api;

import com.google.inject.AbstractModule;
import lombok.EqualsAndHashCode;

/**
 * @author seamonr@gmail.com
 */
@EqualsAndHashCode(callSuper=false, of={})
public class ResourceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(TestResource.class);
    }
}
