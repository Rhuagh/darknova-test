package se.darknova.config.consul;

import com.netflix.archaius.config.polling.PollingResponse;
import com.orbitz.consul.Consul;
import com.orbitz.consul.KeyValueClient;
import com.orbitz.consul.model.kv.Value;
import lombok.Builder;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author seamonr@gmail.com
 */
@lombok.Value
@Builder
public class ConsulConfigReader implements Callable<PollingResponse> {

    private final String host;
    private final String prefix;
    private final int port;

    @Override
    public PollingResponse call() throws Exception {
        KeyValueClient keyValueClient = Consul.newClient(host, port).keyValueClient();
        if (keyValueClient == null) {
            return PollingResponse.noop();
        }
        final Map<String, String> results = new HashMap<>();
        for(Value value : keyValueClient.getValues(prefix)) {
            results.put(value.getKey(), value.getValueAsString().or(""));
        }
        return PollingResponse.forSnapshot(results);
    }
}
