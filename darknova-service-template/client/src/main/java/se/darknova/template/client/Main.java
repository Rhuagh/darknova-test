package se.darknova.template.client;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import se.darknova.discovery.Discovery;
import se.darknova.discovery.DiscoveryModule;

/**
 * @author seamonr@gmail.com
 */
public class Main {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(Stage.PRODUCTION, new DiscoveryModule());
        System.out.println(injector.getInstance(Discovery.class).findService(args[0]));
    }
}
