package se.seb.test.springkafka.messaging;

import se.seb.test.springkafka.model.Order;

public interface OrderMessagingService {
	void sendOrder(Order order);
}
