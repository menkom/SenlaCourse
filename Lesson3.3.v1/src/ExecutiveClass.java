
public class ExecutiveClass {
	// Написать программу, содержащую иерархии цветов для цветочного магазина.
	// Собрать букет и определить его стоимость.
	public static void main(String[] args) {
		Bouquet bouquet = new Bouquet(35);
		
		Florist florist = new Florist(bouquet);
		florist.makeRandomBouquet();
		
		System.out.println(florist.bouquet.toString());
		System.out.println("Price : "+florist.bouquet.getPrice());
		
	}

}
