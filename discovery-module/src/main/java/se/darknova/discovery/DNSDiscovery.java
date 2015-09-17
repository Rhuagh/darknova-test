package se.darknova.discovery;

import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.Record;
import org.xbill.DNS.SRVRecord;
import org.xbill.DNS.TextParseException;
import org.xbill.DNS.Type;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author seamonr@gmail.com
 */
@Slf4j
public class DNSDiscovery implements Discovery {

    public List<Result> findService(String serviceName)  {
        log.info("Attempting to find service {} in DNS", serviceName);
        try {
            Lookup lookup = new Lookup(serviceName, Type.SRV);
            Record[] records = lookup.run();
            switch(lookup.getResult()) {
                case Lookup.SUCCESSFUL:
                    break;
                default:
                    return ImmutableList.of();

            }
            return Arrays.stream(records)
                .map(SRVRecord.class::cast)
                .map(record -> new Result(record.getTarget().toString(true), record.getPort()))
                .collect(Collectors.toList());
        } catch (TextParseException e) {
            System.err.println("Failed parsing service name: " + e);
        }
        return ImmutableList.of();
    }

}
