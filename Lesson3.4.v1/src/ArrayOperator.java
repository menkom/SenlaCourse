
public class ArrayOperator {

	public static boolean hasSpace(Object[] array) {
		boolean result = false;
		for (int i = 0; i < array.length; i++)
			if (array[i] == null) {
				result = true;
				break;
			}
		return result;
	}

	public static int getFreePlace(Object[] array) {
		int result = -1;
		for (int i = 0; i < array.length; i++)
			if (array[i] == null) {
				result = i;
				break;
			}
		return result;
	}

}
