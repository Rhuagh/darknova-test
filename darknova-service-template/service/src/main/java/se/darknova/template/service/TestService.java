package se.darknova.template.service;

import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author seamonr@gmail.com
 */
@Singleton
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class TestService {

    private final TestConfig config;

    public String get() {
        return config.getMessage() + " (at " + this + ")";
    }
}
