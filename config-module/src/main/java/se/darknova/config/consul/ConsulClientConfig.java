package se.darknova.config.consul;

import com.netflix.archaius.annotations.Configuration;
import lombok.Data;

/**
 * @author seamonr@gmail.com
 */
@Configuration(prefix = "consul")
@Data
public class ConsulClientConfig {
    private int refreshRate = 15;
    private String host = "localhost";
    private int port = 8500;
    private String prefix = "test";
}
