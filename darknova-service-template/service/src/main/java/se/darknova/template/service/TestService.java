package se.darknova.template.service;

import javax.inject.Singleton;

/**
 * @author seamonr@gmail.com
 */
@Singleton
public class TestService {

    public String get() {
        return "Hello world TestService " + this;
    }
}
