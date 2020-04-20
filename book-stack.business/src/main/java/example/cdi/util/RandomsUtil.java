package example.cdi.util;

public abstract class RandomsUtil {

	public static int getRandomNumber(int maxValue) {
		return (int) Math.random() * maxValue * 10;
	}
}
