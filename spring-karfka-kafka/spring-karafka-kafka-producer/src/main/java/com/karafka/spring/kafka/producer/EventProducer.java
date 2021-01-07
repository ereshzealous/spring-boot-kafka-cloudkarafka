package com.karafka.spring.kafka.producer;

import com.karafka.spring.kafka.domain.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Created on 06/January/2021 By Author Eresh, Gorantla
 **/
@Slf4j
@RequiredArgsConstructor
@Service
public class EventProducer {
	private final KafkaProperties kafkaProperties;
	private final KafkaTemplate<String, Event> kafkaTemplate;

	public void send(Event event) {
		String kafkaTopic = kafkaProperties.getProducer().getProperties().get("topic");
		log.info("Sending News '{}' to topic '{}'", event, kafkaTopic);
		kafkaTemplate.send(kafkaTopic, event.getId(), event);
	}
}