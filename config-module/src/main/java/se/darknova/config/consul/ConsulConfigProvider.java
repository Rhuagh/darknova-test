package se.darknova.config.consul;

import com.netflix.archaius.Config;
import com.netflix.archaius.config.PollingDynamicConfig;
import com.netflix.archaius.config.polling.FixedPollingStrategy;

import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Provider;
import java.util.concurrent.TimeUnit;

/**
 * @author seamonr@gmail.com
 */
public class ConsulConfigProvider implements Provider<Config> {

    private final ConsulClientConfig config;

    private volatile PollingDynamicConfig dynamicConfig;

    @Inject
    public ConsulConfigProvider(ConsulClientConfig config) {
        this.config = config;
    }

    @Override
    public Config get() {
        ConsulConfigReader reader = ConsulConfigReader.builder()
            .host(config.getHost())
            .port(config.getPort())
            .prefix(config.getPrefix())
            .build();
        return dynamicConfig = new PollingDynamicConfig(reader,
                                                        new FixedPollingStrategy(config.getRefreshRate(),
                                                                                 TimeUnit.SECONDS));
    }

    @PreDestroy
    public void shutdown() {
        if (dynamicConfig != null) {
            dynamicConfig.shutdown();
        }
    }
}
