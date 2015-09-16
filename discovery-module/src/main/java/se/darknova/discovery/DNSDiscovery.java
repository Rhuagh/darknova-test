package se.darknova.discovery;

import com.google.common.collect.ImmutableList;
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
public class DNSDiscovery implements Discovery {

    public DNSDiscovery() {
    }

    public List<Result> findService(String serviceName)  {
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
