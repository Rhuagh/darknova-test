package se.darknova.config.consul;

import com.netflix.archaius.annotations.Configuration;
import lombok.Data;

/**
 * @author seamonr@gmail.com
 */
@Configuration(prefix = "archaius.consul")
@Data
public class ConsulClientConfig {
    private int refreshRate;
    private String host;
    private int port;
    private String prefix;
}
