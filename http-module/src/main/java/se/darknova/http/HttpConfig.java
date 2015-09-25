package se.darknova.http;

import com.netflix.archaius.annotations.Configuration;
import lombok.Data;

/**
 * @author simon
 */
@Configuration(prefix = "se.darknova.http")
@Data
public class HttpConfig {
    private int port;
}
