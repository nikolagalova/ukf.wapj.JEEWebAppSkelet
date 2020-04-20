package example.cdi.enums;

import example.cdi.util.RandomsUtil;

public enum REGULAR_SURNAMES {
	GREEDY("Greedy"), SMELLIE("Smellie"), FLUFFY("Fluffy");

	private String value;

	private REGULAR_SURNAMES(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static REGULAR_SURNAMES getRandom() {
		int randomInd = RandomsUtil.getRandomNumber(values().length);
		return values()[randomInd];
	}

}
