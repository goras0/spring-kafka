package se.seb.test.springkafka.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttributeInfo {
    String pattern;
    String method;

    @Override
    public String toString() {
        return "AttributeInfo{" +
                "pattern='" + pattern + '\'' +
                ", method='" + method + '\'' +
                '}';
    }
}
