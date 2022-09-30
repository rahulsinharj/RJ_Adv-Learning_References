package com.ashok.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.ashok.model.Customer;
import com.ashok.util.KafkaConstants;

/**
 * This class is used to perform business operation
 */

@Service("customerService")
public class CustomerService {

	@Autowired
	private KafkaTemplate<String, Customer> kafkaTemplate;		// predefined class available.

/**	=> Till here, CustomerList Data is coming from RestController.
 * This method is used to publish customer records as msgs to kafka topic.
 * In the KafkaTemplate class -> there a send() method ;
		where we pass the topic which we want to send -> so we pass here the topic name {here topic name = 'customer'} and customer object.

 * Customer object will be converted into JSON ; and JSON data will be stored into KAFKA Topic.
 */
	public String add(List<Customer> customers) {

		if (!customers.isEmpty()) {
			for (Customer c : customers) {
				kafkaTemplate.send(KafkaConstants.TOPIC, c);	// means -> in this topic "customer" we have to store this customer object 'c'.
				System.out.println("************Msg published to Kafka topic***************");
			}
		}
		return "Customer Record Added To Kafka Queue Successfully";
	}
}
