package se.darknova.rest;

import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.AbstractIdleService;
import com.google.inject.Inject;

import com.google.inject.servlet.GuiceFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.jboss.resteasy.plugins.guice.GuiceResteasyBootstrapServletContextListener;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;
import se.darknova.service.GuavaServiceRepository;

import javax.inject.Provider;
import javax.inject.Singleton;
import javax.servlet.DispatcherType;
import java.util.EnumSet;

/**
 *
 */
@Singleton
public class RestServerService extends AbstractIdleService {

    private Server server;

    private final Provider<GuiceResteasyBootstrapServletContextListener> contextListener;

    @Inject
    public RestServerService(GuavaServiceRepository serviceRepository,
                             Provider<GuiceResteasyBootstrapServletContextListener> contextListener) {
        this.contextListener = contextListener;
        serviceRepository.add(this);
    }

    @Override
    protected void startUp() throws Exception {
        Preconditions.checkState(server == null, "Server already started");
        server = new Server(8081);
        final ServletContextHandler sch = new ServletContextHandler(server, "/");
        sch.addFilter(GuiceFilter.class, "/*", EnumSet.allOf(DispatcherType.class));
        sch.addServlet(HttpServletDispatcher.class, "/");
        sch.addEventListener(contextListener.get());
        final HandlerCollection handlers = new HandlerCollection();
        handlers.addHandler(server.getHandler());
        server.setHandler(handlers);
        server.start();
    }

    @Override
    protected void shutDown() throws Exception {
        Preconditions.checkState(server != null, "Server not started");
        server.stop();
    }
}
