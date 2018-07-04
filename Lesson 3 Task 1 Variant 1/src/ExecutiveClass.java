
public class ExecutiveClass {
	// Программа получает массив чисел в строках и возвращает строку с суммой этих
	// чисел
	public static void main(String[] args) {
		// We expect that numbers are of int type
		String[] arrayOfNumbers = { "123", "21", "412", "825", "023", "97" };

//		String[] arrayOfNumbers = { "23", "0", "412", "52", "34", "7" };

		
		ArrayOfNumbers array = new ArrayOfNumbers(arrayOfNumbers);
		
		array.showArray();
		
		System.out.println("Sum of array elements is "+ array.sumOfNumbers());
	}

}
