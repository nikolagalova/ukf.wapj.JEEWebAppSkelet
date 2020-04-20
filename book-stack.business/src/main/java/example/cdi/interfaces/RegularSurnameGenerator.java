package example.cdi.interfaces;

import example.cdi.enums.REGULAR_SURNAMES;
import example.cdi.qualifiers.RegularName;

@RegularName
public class RegularSurnameGenerator implements SurnameGenerator {

	@Override
	public String generateSurname() {
		return REGULAR_SURNAMES.getRandom().getValue();
	}

}
