package se.seb.test.springkafka.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Producer {

    private static final String TOPIC = "events";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    public void sendMessage(String message) {
        this.sendMessage(TOPIC, message);
    }

    public void sendMessage(String topic, String message) {
        log.info(String.format("#### -> Producing message -> %s", message));
        this.kafkaTemplate.send(topic, message);
    }
}