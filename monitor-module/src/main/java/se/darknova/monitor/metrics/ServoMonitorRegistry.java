package se.darknova.monitor.metrics;

import com.netflix.servo.monitor.Monitors;
import lombok.extern.slf4j.Slf4j;

/**
 * @author simon
 */
@Slf4j
public class ServoMonitorRegistry implements MonitorRegistry {

    @Override
    public void registerObject(Object object) {
        log.info("Registering object " + object);
        Monitors.registerObject(object);
    }
}
