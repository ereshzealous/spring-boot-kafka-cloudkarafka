package com.karafka.spring.kafka;

import com.karafka.spring.kafka.domain.Event;
import com.karafka.spring.kafka.domain.EventEnum;
import com.karafka.spring.kafka.domain.MessageEnum;
import com.karafka.spring.kafka.producer.EventProducer;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class SpringKarafkaKafkaProducerApplication {

	private AtomicInteger EVENT_COUNTER = new AtomicInteger();

	public SpringKarafkaKafkaProducerApplication(EventProducer eventProducer) {
		this.eventProducer = eventProducer;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringKarafkaKafkaProducerApplication.class, args);
	}


	final EventProducer eventProducer;


	@Scheduled(fixedRate = 5000)
	public void executeTask() {
		Event event = new Event();
		EventEnum eventEnum = EventEnum.generateRandomEvent();
		event.setId(UUID.randomUUID().toString());
		event.setEvent(eventEnum.getEvent());
		event.setUserName(RandomStringUtils.randomAlphabetic(10));
		event.setData(MessageEnum.getMessage(eventEnum.getEvent()).getMessage());
		eventProducer.send(event);
		log.info("Number of Events Produced :: "+ EVENT_COUNTER.getAndIncrement());
	}

}
