package se.darknova.http;

import lombok.RequiredArgsConstructor;
import org.codehaus.jackson.map.ObjectMapper;

import javax.inject.Inject;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * @author seamonr@gmail.com
 */
@Provider
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class ObjectMapperContextResolver implements ContextResolver<ObjectMapper>{
    private final ObjectMapper objectMapper;

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return objectMapper;
    }
}
