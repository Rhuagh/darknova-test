package se.darknova.client;

import com.google.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import se.darknova.discovery.Discovery;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.core.UriBuilder;
import java.util.List;

/**
 * @author simon
 */
@RequiredArgsConstructor(onConstructor = @__(@Inject))
@Singleton
public class DiscoveryTargetClientFactory {

    private final String URL_TEMPLATE = "http://{host}:{port}";

    private final Provider<Discovery> discovery;

    public <T> T getDiscoveryProxy(String serviceName, Class<T> serviceProxyClass) {
        List<Discovery.Result> results = discovery.get().findService(serviceName);
        if (results.isEmpty()) {
            return null;
        }
        Discovery.Result result = results.get(0);
        ResteasyClient client = new ResteasyClientBuilder().build();
        UriBuilder uriBuilder = UriBuilder.fromUri(URL_TEMPLATE)
            .resolveTemplate("host", result.getHostname())
            .resolveTemplate("port", result.getPort());
        ResteasyWebTarget target = client.target(uriBuilder);
        return target.proxy(serviceProxyClass);
    }

}
