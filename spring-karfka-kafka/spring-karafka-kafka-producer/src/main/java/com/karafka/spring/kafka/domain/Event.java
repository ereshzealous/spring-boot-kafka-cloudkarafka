package com.karafka.spring.kafka.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created on 06/January/2021 By Author Eresh, Gorantla
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
	private String id;
	private String event;
	private String userName;
	private String data;
}