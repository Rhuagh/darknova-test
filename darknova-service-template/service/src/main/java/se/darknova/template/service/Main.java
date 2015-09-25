package se.darknova.template.service;

import com.google.inject.Guice;
import se.darknova.config.consul.ConsulConfigModule;
import se.darknova.monitor.FullMonitorModule;
import se.darknova.http.HttpModule;
import se.darknova.service.GuavaRun;
import se.darknova.service.GuavaServiceModule;
import se.darknova.template.service.api.ResourceModule;

/**
 * @author seamonr@gmail.com
 */
public class Main {

    public static void main(String[] args) {
        Guice.createInjector(new ConsulConfigModule(),
                             new GuavaServiceModule(),
                             new FullMonitorModule(),
                             new HttpModule(),
                             new ResourceModule())
            .getInstance(GuavaRun.class)
            .start();
    }
}
