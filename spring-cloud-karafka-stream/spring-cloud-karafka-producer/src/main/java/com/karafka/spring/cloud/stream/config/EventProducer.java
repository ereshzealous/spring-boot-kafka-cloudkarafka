package com.karafka.spring.cloud.stream.config;

import com.karafka.spring.cloud.stream.domain.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * Created on 06/January/2021 By Author Eresh, Gorantla
 **/
@Slf4j
@RequiredArgsConstructor
@Component
public class EventProducer {

	@Value("${spring.cloud.stream.bindings.news-out-0.destination}")
	private String kafkaTopic;

	private final StreamBridge streamBridge;

	public void send(Event event) {
		log.info("Sending event '{}' to topic '{}'", event, kafkaTopic);

		Message<Event> message = MessageBuilder.withPayload(event)
		                                      .setHeader("partitionKey", event.getId())
		                                      .build();
		streamBridge.send("news-out-0", message);
	}
}