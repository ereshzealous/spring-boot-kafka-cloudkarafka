package com.karafka.spring.kafka.consumer;

import com.karafka.spring.kafka.domain.Event;
import lombok.extern.slf4j.Slf4j;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created on 06/January/2021 By Author Eresh, Gorantla
 **/
@Service
@Slf4j
public class EventConsumer {

	private AtomicInteger COUNTER = new AtomicInteger();

	@KafkaListener(topics = "${spring.kafka.consumer.topic}", groupId = "${spring.kafka.consumer.group-id}")
	public void listen(@Payload Event news,
	                   @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
	                   @Header(KafkaHeaders.RECEIVED_PARTITION_ID) Integer partition,
	                   @Header(KafkaHeaders.OFFSET) Long offset) {
		log.info("Received message\n---\nTOPIC: {}; PARTITION: {}; OFFSET: {};\nPAYLOAD: {}\n---", topic, partition, offset, news);
		log.info("Number of Events Consumed :: " + COUNTER.getAndIncrement());
	}
}