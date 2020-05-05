package se.seb.test.springkafka.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class Consumer {

    @KafkaListener(topics = "users", groupId = "group_id")
    public void consume(String message) throws IOException {
        log.info(String.format("#### -> Consumed message -> %s", message));
    }

    /**
     * By default, records that fail are simply logged and we move on to the next one.
     *
     * @param event
     */
    @KafkaListener(topics = "events", id = "fooGroup" )
    public void listen(String event) {
        log.info("Received: " + event);
        if (event.startsWith("foo")) {
            throw new RuntimeException("failed");
        }
    }

    @KafkaListener(topics = "events.error", id = "errorGroup" )
    public void dltListen(String in) {
        log.info("Received from DLT: " + in);
    }
}
