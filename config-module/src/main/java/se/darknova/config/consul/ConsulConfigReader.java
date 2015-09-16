package se.darknova.config.consul;

import com.netflix.archaius.config.polling.PollingResponse;
import com.orbitz.consul.Consul;
import com.orbitz.consul.KeyValueClient;
import com.orbitz.consul.model.kv.Value;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author seamonr@gmail.com
 */
public class ConsulConfigReader implements Callable<PollingResponse> {

    private final ConsulClientConfig config;

    @Inject
    public ConsulConfigReader(ConsulClientConfig config) {
        this.config = config;
    }

    @Override
    public PollingResponse call() throws Exception {
        KeyValueClient keyValueClient = Consul.newClient(config.getHost(), config.getPort()).keyValueClient();
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
