package se.seb.test.springkafka.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import se.seb.test.springkafka.repo.DataRepo;
import se.seb.test.springkafka.model.Order;

import javax.validation.Valid;

@Component
@Slf4j
public class OrderReceiver {

	private DataRepo ui;

	@Autowired
	public OrderReceiver(DataRepo ui) {
		this.ui = ui;
	}

	@Bean
	public RecordMessageConverter converter() {
		return new StringJsonMessageConverter();
	}

//	@KafkaListener(topics = "orders", errorHandler = "validationErrorHandler", groupId = "foo", containerFactory = "kafkaJsonListenerContainerFactory")
//	public void validatedListener(@Payload @Valid Order order) {
//		ui.displayOrder(order);
//	}

	@KafkaListener(topics = "orders", groupId = "foo")
	public void validatedListener(@Payload Order order) {
		ui.displayOrder(order);
	}

	@KafkaListener(topics = "orders.error", id = "ordersErrorGroup" )
	public void dltListen(String in) {
		log.info("Received from DLT: " + in);
	}

/*	@Bean
	public KafkaListenerErrorHandler validationErrorHandler() {
		return (m, e) -> {
			log.error("Validation error {}, {}", m, e);
		};
	}
 */

//	@KafkaListener(topics = "tacocloud.orders.topic")
//	public void handle(Order order, ConsumerRecord<Order> record) {
//		log.error("Received from partition {} with timestamp {}", record.partition(), record.timestamp());
//		ui.displayOrder(order);
//	}
}
