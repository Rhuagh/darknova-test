package se.darknova.template.service;

import com.netflix.archaius.annotations.Configuration;
import lombok.Data;

/**
 * @author seamonr@gmail.com
 */
@Configuration(prefix = "test")
@Data
public class TestConfig {
    private String message;
}
