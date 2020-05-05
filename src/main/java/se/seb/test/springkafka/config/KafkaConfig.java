package se.seb.test.springkafka.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaConfig {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * We override Spring Boot’s auto-configured container factory with our own.
     * @param configurer
     * @param kafkaConsumerFactory
     * @return
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory kafkaListenerContainerFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
            ConsumerFactory<Object, Object> kafkaConsumerFactory) {
        ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        configurer.configure(factory, kafkaConsumerFactory);

        // The SeekToCurrentErrorHandler discards remaining records from the poll()
        // and performs seek operations on the consumer to reset the offsets
        // so that the discarded records are fetched again on the next poll.
        factory.setErrorHandler(new SeekToCurrentErrorHandler(
                new DeadLetterPublishingRecoverer(kafkaTemplate), new FixedBackOff(0L, 2L)));

        // By default, the error handler tracks the failed record,
        // gives up after 10 delivery attempts and logs the failed record.
        // However, we can also send the failed message to another topic.
        // We call this a dead letter topic.

        return factory;
    }

}
