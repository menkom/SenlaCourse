
public class Human {
	public String name;

	public Human(String name) {
		this.name = name;
		System.out.println(this.getClass().getName() + " created.");
	}

	public String toString() {
		return this.getClass().getName() + " " + name;
	}
}
