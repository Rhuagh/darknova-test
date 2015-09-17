package se.darknova.discovery;

import com.netflix.archaius.annotations.Configuration;
import lombok.Data;

/**
 * @author simon
 */
@Configuration(prefix = "discovery")
@Data
public class DiscoveryConfig {
    public enum Type {
        PROPERTIES,
        DNS
    }

    private Type type = Type.PROPERTIES;
}
