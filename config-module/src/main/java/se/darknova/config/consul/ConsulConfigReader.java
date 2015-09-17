package se.darknova.config.consul;

import com.netflix.archaius.config.polling.PollingResponse;
import com.orbitz.consul.Consul;
import com.orbitz.consul.KeyValueClient;
import com.orbitz.consul.model.kv.Value;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author seamonr@gmail.com
 */
@ToString
@RequiredArgsConstructor(onConstructor = @__(@Inject))
@Slf4j
public class ConsulConfigReader implements Callable<PollingResponse> {

    private final ConsulClientConfig config;

    @Override
    public PollingResponse call() throws Exception {
        if (!config.isEnabled()) {
            return PollingResponse.noop();
        }
        log.info("Refreshing configuration from {}:{}/{}",
                 config.getHost(),
                 config.getPort(),
                 config.getPrefix());
        KeyValueClient keyValueClient = Consul.newClient(config.getHost(),
                                                         config.getPort()).keyValueClient();
        if (keyValueClient == null) {
            return PollingResponse.noop();
        }
        final Map<String, String> results = new HashMap<>();
        for(Value value : keyValueClient.getValues(config.getPrefix())) {
            results.put(value.getKey(), value.getValueAsString().or(""));
        }
        return PollingResponse.forSnapshot(results);
    }
}
