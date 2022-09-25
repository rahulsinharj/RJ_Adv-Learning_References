package com.ashok.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.ashok.model.Customer;
import com.ashok.util.KafkaConstants;

@Configuration
public class KafkaProduceConfig {
	
	/**
	 * This method is used to Kafka Producer Config details
	 * @return
	 */

	@Bean
	public ProducerFactory<String, Customer> producerFactory() {
		Map<String, Object> configProps = new HashMap<String, Object>();
		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.HOST);			// BOOTSTRAP_SERVERS = i.e, to connect to Kafka Server, where your Kafka server is Running -> the URL of Kafka Server.
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);	// KEY_SERIALIZER = i.e, Topic Name
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);	// VALUE_SERIALIZER = i.e, Customer Data
		return new DefaultKafkaProducerFactory(configProps);
	}

	/**
	 * This method is used to create KafkaTemplate bean obj
	 * @return
	 */
	@Bean(name = "kafkaTemplate")
	public KafkaTemplate<String, Customer> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}

}
