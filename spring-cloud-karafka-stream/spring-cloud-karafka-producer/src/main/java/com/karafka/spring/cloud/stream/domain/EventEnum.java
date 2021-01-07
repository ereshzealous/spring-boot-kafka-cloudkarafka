package com.karafka.spring.cloud.stream.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Random;

/**
 * Created on 06/January/2021 By Author Eresh, Gorantla
 **/
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum EventEnum {
	INSERTED("Inserted"),
	UPDATED("Updated"),
	DELETED("Deleted"),
	APPROVED("Approved"),
	REJECTED("Rejected"),
	DENIED("Denied"),
	ON_HOLD("On Hold");
	private String event;

	public static EventEnum generateRandomEvent() {
		EventEnum[] eventEnums = EventEnum.values();
		Random random = new Random();
		final Integer index = random.ints(0, (eventEnums.length - 1)).limit(1).findFirst().getAsInt();
		return eventEnums[index];
	}
}