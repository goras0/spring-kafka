package se.seb.test.springkafka;

import se.seb.test.springkafka.model.Order;


public class DataRepo {

	public void displayOrder(Order order) {
		System.out.println("Recived order : " + order.toString());		
	}

}
