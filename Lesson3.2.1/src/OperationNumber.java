//import java.util.Arrays;

public class OperationNumber {
	int workingNumber;

	public OperationNumber() {
		workingNumber = generateRandomNumber();
	}

	public static int generateRandomNumber() {
		// generation of three digit number (nextInt(exclude 900))
		return (new java.util.Random()).nextInt(900) + 100;
	}

	public int[] numberToArray(int number) {
		int[] result = new int[lengthOfNumber(number)];

		int PreviousNumber = 0;
		int tempDigit = number;

		for (int i = result.length - 1; i >= 0; i--) {
			PreviousNumber = tempDigit % 10;
			tempDigit /= 10;
			result[i] = (byte) PreviousNumber;
		}

//		System.out.println(Arrays.toString(result));

		return result;
	}

	public int getMaxNumber() {
		int[] arrayOfDigits = numberToArray(workingNumber);
		int result = arrayOfDigits[0];

		for (int i = 1; i < arrayOfDigits.length; i++) {
			result = Math.max(result, arrayOfDigits[i]);
		}
		return result;
	}

	public int lengthOfNumber(int number) {
		return (int) (Math.log10(number) + 1);
	}
}
