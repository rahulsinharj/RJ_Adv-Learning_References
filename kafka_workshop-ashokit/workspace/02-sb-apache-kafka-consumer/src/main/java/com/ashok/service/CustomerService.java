package com.ashok.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.ashok.model.Customer;
import com.ashok.util.KafkaConstants;

/**
 * This class is used to perform business operation
 */

@Service("customerService")
public class CustomerService {

/**
 * This method is used to consumer messages from kafka topic ; this method will act as a Listner.
 * In the Kafka, Data is stored in the form of 'JSON' (not Object), But our below listner() method is expecting 'Object'.
 * 		Therefore, in Consumer Application side -> the ConsumerFactory DESERIALIZER_CLASS_CONFIG in KafkaListenerConfig class is actually converting the coming JSON data into Object.
 *
 */
	@KafkaListener(topics = KafkaConstants.TOPIC, groupId = KafkaConstants.GROUP_ID)
	public Customer listener(Customer c) {
		System.out.println("***Msg recieved from Kafka Topic ::" + c);
		return c;
	}

}
