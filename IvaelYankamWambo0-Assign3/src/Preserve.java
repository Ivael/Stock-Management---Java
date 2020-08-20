import java.util.Scanner;

/**
 * 
 * @author Student Number Given class Fruit extends class FoodItem version 2.0
 */
public class Preserve extends FoodItem {

	private int jarSize;

	/**
	 * Default Constructor
	 */
	public Preserve() {
		super();
		jarSize = 0;
	}

	@Override
	public boolean addItem(Scanner input) {
		boolean bool = false;
		if (super.addItem(input))

		{
			// Input quantity
			while (!bool) {
				System.out.print("Enter the size of the jar in millilitres: ");
				if (input.hasNextInt()) {
					jarSize = input.nextInt();
					if (jarSize < 0) {
						bool = false;
						System.out.println("Invalid input");
						jarSize = 0;
					} else

						bool = true;
				} else {
					System.out.println("Invalid input");
					input.next();
					bool = false;
				}
			}
		}
		return true;
	}

	public String toString() {
		return super.toString() + " size: " + jarSize + "mL";
	}

}
