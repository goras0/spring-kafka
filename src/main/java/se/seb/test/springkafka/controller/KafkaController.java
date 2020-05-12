package se.seb.test.springkafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.seb.test.springkafka.config.ConfigProperties;
import se.seb.test.springkafka.messaging.Producer;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    private final Producer producer;

    @Autowired
    KafkaController(Producer producer) {
        this.producer = producer;
    }

    @Autowired
    ConfigProperties config;

    @GetMapping(value = "/config")
    public void config() {
        System.out.println("Config: " + config);
    }

    @PostMapping(value = "/{topic}")
    public void sendMessageToKafkaTopic(@PathVariable("topic") String topic, @RequestBody String message) {
        this.producer.sendMessage(topic, message);
    }
}
