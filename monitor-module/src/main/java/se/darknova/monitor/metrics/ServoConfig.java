package se.darknova.monitor.metrics;

import com.netflix.archaius.annotations.Configuration;
import lombok.Data;

/**
 * @author simon
 */
@Data
@Configuration(prefix = "se.darknova.metrics.servo")
public class ServoConfig {
    private boolean graphiteEnabled = false;
    private String graphiteServerAddress;
    private String graphiteServerPrefix;
    private boolean cloudwatchEnabled = false;
    private String cloudwatchNamespace;
    private String awsAccessKeyId;
    private String awsSecretKey;
    private boolean jvmPollerEnabled = false;
    private int asynchronousQueueSize = 10;
    private int pollInterval = 10;
}