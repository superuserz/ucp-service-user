package com.nagp.ucp.user.entity;

public enum UserTypeEnum {

	/** The agent. */
	REGULAR("REGULAR"),
	/** The office manager. */
	PROVIDER("PROVIDER"),

	ADMIN("ADMIN");

	private String value;

	private UserTypeEnum(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static UserTypeEnum parse(final String value) {
		UserTypeEnum userTypeEnum = null; // Default
		for (UserTypeEnum item : UserTypeEnum.values()) {
			if (item.getValue().equals(value)) {
				userTypeEnum = item;
				break;
			}
		}
		return userTypeEnum;
	}

}
