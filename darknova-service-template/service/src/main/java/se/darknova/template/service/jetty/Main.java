package se.darknova.template.service.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import se.darknova.template.service.jersey.Application;

/**
 * @author seamonr@gmail.com
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        ServletContextHandler sch = new ServletContextHandler(server, "/");
        sch.addServlet(DefaultServlet.class, "/");
        ServletHolder servletHolder = new ServletHolder(new ServletContainer());
        servletHolder.setInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, Application.class.getCanonicalName());
        sch.addServlet(servletHolder, "/api/*");
        server.start();
        server.join();
    }
}
