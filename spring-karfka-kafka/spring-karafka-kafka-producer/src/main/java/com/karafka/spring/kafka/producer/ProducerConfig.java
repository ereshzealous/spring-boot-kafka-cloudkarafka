package com.karafka.spring.kafka.producer;

import com.karafka.spring.kafka.domain.Event;
import lombok.RequiredArgsConstructor;

import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Map;

/**
 * Created on 06/January/2021 By Author Eresh, Gorantla
 **/
@Configuration
@RequiredArgsConstructor
public class ProducerConfig {

	private final KafkaProperties kafkaProperties;

	@Bean
	ProducerFactory<String, Event> producerFactory() {
		Map<String, Object> props = kafkaProperties.buildProducerProperties();
		props.put(org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		props.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
		return new DefaultKafkaProducerFactory<>(props);
	}

	@Bean
	public KafkaTemplate<String, Event> kafkaTemplate(ProducerFactory<String, Event> producerFactory) {
		return new KafkaTemplate<>(producerFactory);
	}
}