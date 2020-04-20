package example.cdi.interfaces;

import example.cdi.enums.SCIENCE_SURNAMES;
import example.cdi.qualifiers.ScientistName;

@ScientistName
public class ScientistSurnameGenerator implements SurnameGenerator {

	@Override
	public String generateSurname() {
		return SCIENCE_SURNAMES.getRandom().getValue();
	}

}
