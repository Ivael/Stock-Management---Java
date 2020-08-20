import java.util.Scanner;

/**
 * 
 * @author Student Number Given class Fruit extends class FoodItem version 2.0
 */
public class Fruit extends FoodItem {
	/**
	 * Name of the orchard
	 */
	private String orchardName;

	/**
	 * Default Constructor
	 */
	public Fruit() {
		super();
		orchardName = "";
	}

	@Override
	// Input Orchrd supplier

	public boolean addItem(Scanner scanner) {
		if (super.addItem(scanner)) {
			System.out.print("Enter the name of the orchard supplier: ");
			orchardName = scanner.next();
		}
		return true;
	}

	@Override
	// toString method to display orchard supplier

	public String toString() {
		return super.toString() + " orchard supplier: " + orchardName;
	}
}
