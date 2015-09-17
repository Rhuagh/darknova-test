package se.darknova.rest;

import com.netflix.archaius.annotations.Configuration;
import lombok.Data;

/**
 * @author simon
 */
@Configuration(prefix = "rest.http")
@Data
public class RestConfig {
    private int port;
}
