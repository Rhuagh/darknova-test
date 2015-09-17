package se.darknova.discovery;

import com.netflix.archaius.Config;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * @author simon
 */
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class DiscoveryProvider implements Provider<Discovery> {

    private final DiscoveryConfig discoveryConfig;
    private final Config config;

    @Override
    public Discovery get() {
        switch(discoveryConfig.getType()) {
            case DNS:
                return new DNSDiscovery();
            default:
                return new PropertiesDiscovery(config);
        }
    }

}
