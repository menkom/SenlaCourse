
public abstract class AFlower {
	public int price;
	public String name;

	public AFlower(int price) {
		super();
		this.price = price;
		this.name = this.getClass().getName();
	}

    public String toString() {
    	return "Flower: name - "+ name+", price - "+price;
    }
}
