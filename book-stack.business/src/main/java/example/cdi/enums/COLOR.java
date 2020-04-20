package example.cdi.enums;

import example.cdi.util.RandomsUtil;

public enum COLOR {
	RED, BLACK, WHITE, TRICOLORA, PANDA, RAINBOW;
	
	public static COLOR getRandomColor() {
		int randomInd = RandomsUtil.getRandomNumber(values().length);
		return values()[randomInd];
	}
}
