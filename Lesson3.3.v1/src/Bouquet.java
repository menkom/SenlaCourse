
public class Bouquet {
	public AFlower[] flowers;

	public Bouquet(int FlowersInBouquet) {
		this.flowers = new AFlower[FlowersInBouquet];
	}

	public void addFlower(AFlower flower) {
		if (isSpace()) {
			flowers[getFreeSpace()] = flower;
		}
	}

	public String toString() {
		String result = "Bouquet:";
		for (int i = 0; i < flowers.length; i++) {
			if (flowers[i] != null) {
				result += "\n " + flowers[i].toString();
			}
		}
		return result;
	}

	public int getPrice() {
		int result = 0;

		for (int i = 0; i < flowers.length; i++) {
			if (flowers[i] != null) {
				result += flowers[i].price;
			}
		}

		return result;
	}

	private boolean isSpace() {
		boolean result = false;
		for (int i = 0; i < flowers.length; i++)
			if (flowers[i] == null) {
				result = true;
				break;
			}
		return result;
	}

	private int getFreeSpace() {
		int result = -1;
		for (int i = 0; i < flowers.length; i++)
			if (flowers[i] == null) {
				result = i;
				break;
			}
		return result;
	}

}
