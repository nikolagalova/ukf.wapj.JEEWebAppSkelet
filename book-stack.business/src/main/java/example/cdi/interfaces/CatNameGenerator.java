package example.cdi.interfaces;

public class CatNameGenerator implements FirstNameGenerator {

	@Override
	public String generateName() {
		return "Kitty";
	}

}
