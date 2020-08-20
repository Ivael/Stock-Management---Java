import java.util.Scanner;

/**
 * @author Student Number Given class Vegetable extends class FoodItem
 * @version 2.0
 */
public class Vegetable extends FoodItem {
	/**
	 * name of the farm
	 */
	private String farmName;

	/**
	 * Default Constructor
	 */
	public Vegetable() {
		super();
		farmName = "";
	}

	@Override
	public boolean addItem(Scanner input) {
		if (super.addItem(input)) {
			System.out.print("Enter the name of the farm supplier: ");
			farmName = input.next();
		}
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + " farm supplier: " + farmName;
	}
}
