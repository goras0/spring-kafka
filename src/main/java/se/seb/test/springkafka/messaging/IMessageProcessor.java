package se.seb.test.springkafka.messaging;

public interface IMessageProcessor {
    void process(String key, String json);
}
