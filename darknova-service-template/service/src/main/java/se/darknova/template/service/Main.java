package se.darknova.template.service;

import com.google.inject.Guice;
import com.google.inject.Injector;
import se.darknova.rest.RestModule;
import se.darknova.service.GuavaRun;
import se.darknova.service.GuavaServiceModule;
import se.darknova.template.service.api.ResourceModule;

/**
 * @author seamonr@gmail.com
 */
public class Main {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new GuavaServiceModule(),
                                                 new RestModule(),
                                                 new ResourceModule());
        injector.getInstance(GuavaRun.class).start();
    }
}
