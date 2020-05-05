package se.seb.test.springkafka.messaging;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;

public class CustomAckMessageListener implements AcknowledgingMessageListener<String, String> {

    // inject your own concrete processor
    private IMessageProcessor messageProcessor;

    @Override
    public void onMessage(ConsumerRecord<String, String> consumerRecord, Acknowledgment acknowledgment) {

        // process message
        messageProcessor.process(consumerRecord.key(), consumerRecord.value());

        // commit offset
        acknowledgment.acknowledge();
    }
}
