package se.seb.test.springkafka.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import se.seb.test.springkafka.repo.DataRepo;
import se.seb.test.springkafka.model.Order;

@Component
public class OrderReceiver {

	private DataRepo ui;

	@Autowired
	public OrderReceiver(DataRepo ui) {
		this.ui = ui;
	}

	@KafkaListener(topics = "orders-topic", groupId = "foo")
	public void handle(Order order) {
		ui.displayOrder(order);
	}

//	@KafkaListener(topics = "tacocloud.orders.topic")
//	public void handle(Order order, ConsumerRecord<Order> record) {
//		log.error("Received from partition {} with timestamp {}", record.partition(), record.timestamp());
//		ui.displayOrder(order);
//	}
}
