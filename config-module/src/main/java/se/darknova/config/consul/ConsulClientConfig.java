package se.darknova.config.consul;

import com.netflix.archaius.annotations.Configuration;
import lombok.Data;

/**
 * @author seamonr@gmail.com
 */
@Configuration(prefix = "archaius.consul")
@Data
public class ConsulClientConfig {
    private int refreshRate = 30;
    private String host = null;
    private int port = 0;
    private String prefix = null;
}
