import java.util.Arrays;

public class ArrayOfNumbers {

	String[] array;
	
	public ArrayOfNumbers(String[] aArray) {
		array = aArray;
	}
	
	public String sumOfNumbers() {
		int resultInt = 0;

		for (String arrayItem : this.array) {
			try {
				resultInt += Integer.parseInt(arrayItem);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return Integer.toString(resultInt);
	}

	public void showArray() {
		System.out.println(Arrays.toString(this.array));
	}

}
