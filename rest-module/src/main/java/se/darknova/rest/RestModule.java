package se.darknova.rest;

import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.servlet.RequestScoped;
import com.google.inject.servlet.ServletModule;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.jboss.resteasy.plugins.guice.GuiceResteasyBootstrapServletContextListener;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

/**
 * @author seamonr@gmail.com
 */
@EqualsAndHashCode(callSuper=false, of={})
public class RestModule extends ServletModule {

    @Override
    protected void configureServlets() {
        bind(Request.class).toProvider(new ResteasyContextProvider<>(Request.class)).in(RequestScoped.class);
        bind(HttpHeaders.class).toProvider(new ResteasyContextProvider<>(HttpHeaders.class)).in(RequestScoped.class);
        bind(UriInfo.class).toProvider(new ResteasyContextProvider<>(UriInfo.class)).in(RequestScoped.class);
        bind(SecurityContext.class).toProvider(new ResteasyContextProvider<>(SecurityContext.class)).in(RequestScoped.class);

        bind(GuiceResteasyBootstrapServletContextListener.class);
        bind(ObjectMapperContextResolver.class).in(Singleton.class);

        bind(RestServerService.class).asEagerSingleton();
    }

    @RequiredArgsConstructor
    private static class ResteasyContextProvider<T> implements Provider<T> {

        private final Class<T> instanceClass;

        @Override
        public T get() {
            return ResteasyProviderFactory.getContextData(instanceClass);
        }
    }
}
