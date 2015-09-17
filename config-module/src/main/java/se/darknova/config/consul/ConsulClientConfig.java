package se.darknova.config.consul;

import com.netflix.archaius.annotations.Configuration;
import lombok.Data;

/**
 * @author seamonr@gmail.com
 */
@Configuration(prefix = "consul")
@Data
public class ConsulClientConfig {
    private boolean enabled = false;
    private int refreshRate = 30;
    private String host = "localhost";
    private int port = 8500;
    private String prefix = "";
}
