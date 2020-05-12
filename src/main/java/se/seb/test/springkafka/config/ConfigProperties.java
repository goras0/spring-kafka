package se.seb.test.springkafka.config;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "anonym")
public class ConfigProperties {

    private List<String> defaultRecipients;
    private Map<String, String> additionalHeaders;
    private List<Source> source;



    @Override
    public String toString() {
        return "ConfigProperties{" +
                ", defaultRecipients=" + defaultRecipients +
                ", additionalHeaders=" + additionalHeaders +
                ", source=" + source +
                 '}';
    }
}
