
public class ExecutiveClass {
	// Программа программа выводит на экран случайно сгенерированное трехзначное
	// натурально число и его наибольшую цифру
	public static void main(String[] args) {

		OperationNumber randNumber = new OperationNumber();

		System.out.println(randNumber.getMaxNumber() + " is max number in " + randNumber.workingNumber);
	}

}
