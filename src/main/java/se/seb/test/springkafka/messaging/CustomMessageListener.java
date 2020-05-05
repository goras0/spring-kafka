package se.seb.test.springkafka.messaging;

import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;

@AllArgsConstructor
public class CustomMessageListener implements MessageListener<String, String> {

    // inject your own concrete processor
    private IMessageProcessor messageProcessor;

    @Override
    public void onMessage(ConsumerRecord<String, String> consumerRecord) {

        // process message
        messageProcessor.process(consumerRecord.key(), consumerRecord.value());
    }
}