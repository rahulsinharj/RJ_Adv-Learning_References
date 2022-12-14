package com.ashok.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.ashok.model.Customer;
import com.ashok.util.KafkaConstants;

@Configuration
@EnableKafka								// Annotation to enable KAFKA Listener here
public class KafkaListenerConfig {

	/**		Below method is used provide Kafka Consumer Config details : for making this "Consumer_Factory"	*/
	@Bean
	public ConsumerFactory<String, Customer> consumerFactory() {
		Map<String, Object> props = new HashMap();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.HOST);				// BOOTSTRAP_SERVERS = i.e, to connect to Kafka Server, where your Kafka server is Running -> the URL of Kafka Server.
		props.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaConstants.GROUP_ID);						// GROUP_ID = i.e, For every Consumer we can give 1 GROUP_ID as an Identifier, i.e, Multiple messages can be stored as a Single Group.
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);		// KEY_DESERIALIZER = i.e, Topic Name
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);		// VALUE_DESERIALIZER = i.e, Customer Data

		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(Customer.class));
	}

/**		Below method is used to inject the above "Consumer_Factory" with the "Concurrent_Kafka_Listener_Container_Factory"	*/
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Customer> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Customer> factory = new ConcurrentKafkaListenerContainerFactory<String, Customer>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

}
