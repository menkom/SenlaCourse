
public class ExecutiveClass {
	// �������� ���������, ���������� �������� ������ ��� ���������� ��������.
	// ������� ����� � ���������� ��� ���������.
	public static void main(String[] args) {
		Bouquet bouquet = new Bouquet(35);
		
		Florist florist = new Florist(bouquet);
		florist.makeRandomBouquet();
		
		System.out.println(florist.bouquet.toString());
		System.out.println("Price : "+florist.bouquet.getPrice());
		
	}

}
