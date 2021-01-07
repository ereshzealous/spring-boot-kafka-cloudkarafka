package com.karafka.spring.kafka.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * Created on 06/January/2021 By Author Eresh, Gorantla
 **/
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum MessageEnum {

	INSERTED_MESSAGE(EventEnum.INSERTED.getEvent(), "User has been inserted in the System"),
	UPDATED_MESSAGE(EventEnum.UPDATED.getEvent(), "User has been updated in the System"),
	DELETED_MESSAGE(EventEnum.DELETED.getEvent(), "User has been removed from the System"),
	APPROVED_MESSAGE(EventEnum.APPROVED.getEvent(), "User Claim has been Approved"),
	REJECTED_MESSAGE(EventEnum.REJECTED.getEvent(), "User Claim has been Rejected"),
	DENIED_MESSAGE(EventEnum.DENIED.getEvent(), "User Claim has been denied"),
	ON_HOLD_MESSAGE(EventEnum.ON_HOLD.getEvent(), "User Claim changed to ON HOLD");

	private String event;
	private String message;

	public static MessageEnum getMessage(String event) {
		return Arrays.stream(MessageEnum.values()).filter(data -> StringUtils.equalsIgnoreCase(data.getEvent(), event)).findFirst().orElse(null);
	}
}