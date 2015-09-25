package se.darknova.template.client;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.netflix.archaius.guice.ArchaiusModule;
import se.darknova.template.api.Test;
import se.darknova.template.client.api.TestClientModule;

/**
 * @author seamonr@gmail.com
 */
public class Main {


    public static void main(String[] args) {
        Injector injector = Guice.createInjector(Stage.DEVELOPMENT,
                                                 new ArchaiusModule(),
                                                 new TestClientModule());
        System.out.println(injector.getInstance(Test.class).get());
    }
}
