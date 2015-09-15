package se.darknova.template.client;

import com.google.inject.Guice;
import com.google.inject.Injector;
import se.darknova.template.client.discovery.Discovery;
import se.darknova.template.client.discovery.DiscoveryModule;

/**
 * @author seamonr@gmail.com
 */
public class Main {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new DiscoveryModule());
        System.out.println(injector.getInstance(Discovery.class).findService(args[0]));
    }
}
