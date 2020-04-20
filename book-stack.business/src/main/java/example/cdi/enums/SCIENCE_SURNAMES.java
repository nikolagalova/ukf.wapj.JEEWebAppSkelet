package example.cdi.enums;

import example.cdi.util.RandomsUtil;

public enum SCIENCE_SURNAMES {
	
	SCHRODINGER("Schrödinger"), EINSTEIN("Einstein"), GALILEO("Galileo"), COPPERNICUS("Copernicus"), FEYNMANN("Feynmann");
	
	private String value;
	
	private SCIENCE_SURNAMES(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	public static SCIENCE_SURNAMES getRandom() {
		int randomInd = RandomsUtil.getRandomNumber(values().length);
		return values()[randomInd];
	}

}
