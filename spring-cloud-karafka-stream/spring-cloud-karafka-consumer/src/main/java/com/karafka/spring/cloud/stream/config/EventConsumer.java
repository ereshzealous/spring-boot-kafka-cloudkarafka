package com.karafka.spring.cloud.stream.config;

import com.karafka.spring.cloud.stream.domain.Event;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * Created on 06/January/2021 By Author Eresh, Gorantla
 **/
@Component
@Slf4j
public class EventConsumer {

	private AtomicInteger COUNTER = new AtomicInteger();

	@Bean
	public Consumer<Message<Event>> news() {
		return message -> {
			Event event = message.getPayload();
			MessageHeaders messageHeaders = message.getHeaders();
			log.info("Received message\n---\nTOPIC: {}; PARTITION: {}; OFFSET: {};\nPAYLOAD: {}\n---",
			         messageHeaders.get(KafkaHeaders.RECEIVED_TOPIC, String.class),
			         messageHeaders.get(KafkaHeaders.RECEIVED_PARTITION_ID, Integer.class),
			         messageHeaders.get(KafkaHeaders.OFFSET, Long.class),
			         event);
			log.info("Number of Events Consumed :: " + COUNTER.getAndIncrement());
		};
	}
}