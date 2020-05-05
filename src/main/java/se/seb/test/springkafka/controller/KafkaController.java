package se.seb.test.springkafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.seb.test.springkafka.messaging.Producer;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    private final Producer producer;

    @Autowired
    KafkaController(Producer producer) {
        this.producer = producer;
    }

    @PostMapping(value = "/{topic}")
    public void sendMessageToKafkaTopic(@PathVariable("topic") String topic, @RequestParam("message") String message) {
        this.producer.sendMessage(topic, message);
    }
}
