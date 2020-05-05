package se.seb.test.springkafka.config;

import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListenerConfigurer;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerEndpointRegistrar;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.util.backoff.FixedBackOff;
import se.seb.test.springkafka.messaging.MyValidator;

@Configuration
public class KafkaConfig implements KafkaListenerConfigurer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     *  validate @KafkaListener @Payload arguments
     * @param registrar
     */
   @Override
    public void configureKafkaListeners(KafkaListenerEndpointRegistrar registrar) {
     //   registrar.setValidator(new MyValidator());
    }


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

/*    @Bean
    public DefaultKafkaProducerFactory pf(KafkaProperties properties) {
        Map<String, Object> props = properties.buildProducerProperties();
        DefaultKafkaProducerFactory pf = new DefaultKafkaProducerFactory(props,
                new StringSerializer(),
                new JsonSerializer<>(MyValueType.class)
                        .noTypeInfo());
    }

    @Bean
    public DefaultKafkaConsumerFactory pf(KafkaProperties properties) {
        Map<String, Object> props = properties.buildConsumerProperties();
        DefaultKafkaConsumerFactory pf = new DefaultKafkaConsumerFactory(props,
                new JsonDeserializer<>(MyKeyType.class)
                        .forKeys()
                        .ignoreTypeHeaders(),
                new JsonSerializer<>(MyValueType.class)
                        .ignoreTypeHeaders());
    }
*/
}
