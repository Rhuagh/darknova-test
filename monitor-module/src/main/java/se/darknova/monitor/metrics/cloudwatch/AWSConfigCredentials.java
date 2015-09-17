package se.darknova.monitor.metrics.cloudwatch;

import com.amazonaws.auth.AWSCredentials;
import lombok.Value;
import se.darknova.monitor.metrics.ServoConfig;

/**
 * @author simon
 */
@Value
public class AWSConfigCredentials implements AWSCredentials {

    private final String aWSSecretKey;
    private final String aWSAccessKeyId;

    public AWSConfigCredentials(ServoConfig servoConfig) {
        this.aWSAccessKeyId = servoConfig.getAwsAccessKeyId();
        this.aWSSecretKey = servoConfig.getAwsSecretKey();
    }
}
