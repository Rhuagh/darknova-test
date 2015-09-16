package se.darknova.discovery;

import lombok.Value;

import java.util.List;

/**
 * @author seamonr@gmail.com
 */
public interface Discovery {
    @Value
    class Result {

        private final String hostname;
        private final int port;
    }

    List<Result> findService(String serviceName);
}
