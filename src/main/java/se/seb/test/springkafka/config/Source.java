package se.seb.test.springkafka.config;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Source {
    String path;
    String type;
    List<AttributeInfo> attributeInfo;

    @Override
    public String toString() {
        return "Source{" +
                "path='" + path + '\'' +
                ", type='" + type + '\'' +
                ", attributeInfo=" + attributeInfo +
                '}';
    }
}
