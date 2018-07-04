import java.util.Random;

public class Florist {
	Bouquet bouquet;

	public Florist(Bouquet aBouquet) {
		this.bouquet = aBouquet;
	}

	public void addTulip() {
		this.bouquet.addFlower(new Tulip());
	}

	public void addRose() {
		this.bouquet.addFlower(new Rose());
	}

	public void addChamomile() {
		this.bouquet.addFlower(new Chamomile());
	}

	public void addChrysantemum() {
		this.bouquet.addFlower(new Chrysantemum());
	}

	public void addSunflower() {
		this.bouquet.addFlower(new Sunflower());
	}

	public void addTulips(int numberOfFlowers) {
		for (int i = 0; i < numberOfFlowers; i++)
			addTulip();
	}

	public void addRoses(int numberOfFlowers) {
		for (int i = 0; i < numberOfFlowers; i++)
			addRose();
	}

	public void addChrysantemums(int numberOfFlowers) {
		for (int i = 0; i < numberOfFlowers; i++)
			addChrysantemum();
	}

	public void addChamomiles(int numberOfFlowers) {
		for (int i = 0; i < numberOfFlowers; i++)
			addChamomile();
	}

	public void addSunflowers(int numberOfFlowers) {
		for (int i = 0; i < numberOfFlowers; i++)
			addSunflower();
	}

	public void makeRandomBouquet() {
		int space = bouquet.flowers.length;
		Random rndm = new Random();
	
		int flowersNumber = rndm.nextInt(space);
		addChamomiles(flowersNumber);
		space -= flowersNumber;

		flowersNumber = rndm.nextInt(space);
		addRoses(flowersNumber);
		space -= flowersNumber;

		flowersNumber = rndm.nextInt(space);
		addTulips(flowersNumber);
		space -= flowersNumber;

		flowersNumber = rndm.nextInt(space);
		addSunflowers(flowersNumber);
		space -= flowersNumber;

		flowersNumber = rndm.nextInt(space);
		addChrysantemums(flowersNumber);
		space -= flowersNumber;

	}
}
