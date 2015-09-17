package se.darknova.discovery;

import com.google.common.collect.ImmutableList;
import com.netflix.archaius.Config;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;

/**
 * @author simon
 */
@RequiredArgsConstructor
@Slf4j
public class PropertiesDiscovery implements Discovery {

    private final Config config;

    @Override
    public List<Result> findService(String serviceName) {
        log.info("Attempting to find service {} in properties", serviceName);
        String baseKey = "discovery." + serviceName + ".";
        if (config.containsKey(baseKey + "host") && config.containsKey(baseKey + "port")) {
            return Collections.singletonList(new Result(config.getString(baseKey + "host"),
                                                        config.getInteger(baseKey + "port")));
        }
        return ImmutableList.of();
    }
}
