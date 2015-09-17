package se.darknova.monitor.metrics;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.Matchers;
import com.google.inject.spi.InjectionListener;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;
import lombok.EqualsAndHashCode;

/**
 * @author simon
 */
@EqualsAndHashCode(callSuper = false, of = {})
public class ServoMetricsModule extends AbstractModule {

    @Override
    protected void configure() {
        MonitorRegistry registry = new ServoMonitorRegistry();
        bind(MonitorRegistry.class).toInstance(registry);
        InjectionListener<Object> injectionListener = registry::registerObject;
        TypeListener typeListener = new TypeListener() {
            @Override
            public <I> void hear(TypeLiteral<I> type, TypeEncounter<I> encounter) {
                if (type.getRawType().isAnnotationPresent(MonitorClass.class)) {
                    encounter.register(injectionListener);
                }
            }
        };
        bindListener(Matchers.any(), typeListener);
    }
}
