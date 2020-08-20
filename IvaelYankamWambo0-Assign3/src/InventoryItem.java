import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 
 * @author This class contains the behaviour of an inventory Student Number
 *         version 2.0
 */
public class InventoryItem {
	private Queue<LocalDate> expdate;
	private FoodItem item;
	private int itemQuantityInStock;

	public InventoryItem(FoodItem Fitem) {
		itemQuantityInStock = 0;
		this.item = Fitem;
		expdate = new LinkedList<LocalDate>();
	}

	public boolean addItem(Scanner input) {
		boolean bool = false;
		if (item == null)
			return false;
		if (item.addItem(input)) {
			// Input quantity
			while (!bool) {
				System.out.print("Enter the quantity for the item: ");
				if (input.hasNextInt()) {
					int temp = input.nextInt();
					if (temp < 0) {
						bool = false;
						System.out.println("Invalid input");
					}

					else

					{
						bool = true;
						return updateQuantity(input, temp);
					}
				} else {
					System.out.println("Invalid input");
					input.next();
					bool = false;
				}
			}
		}
		return bool;
	}

	/**
	 * Returns the item code so it may be used to compare objects
	 *
	 * @return Value of itemCode of the item data member
	 */

	public int getItemCode() {
		if (item != null)
			return item.getItemCode();
		return -1;
	}

	public boolean inputCode(Scanner input) {
		if (item == null)
			item = new FoodItem();
		return item.inputCode(input);
	}

	/**
	 * Prints the summary of the expiry dates for this item
	 */
	public void printExpirySummary() {
		System.out.println(toString());
		System.out.println("Expiry Details:");
		if (expdate.isEmpty()) {
			System.out.println("None");
			return;
		}
		Iterator<LocalDate> list = expdate.iterator();
		int track = 0;
		LocalDate compareTo = expdate.peek();
		if (compareTo != null && compareTo.equals(LocalDate.MAX))
			System.out.print("No Expiry: ");
		else
			System.out.print(compareTo + ": ");
		while (list.hasNext()) {
			LocalDate current = list.next();

			if (current.equals(compareTo))
				track++;
			else {
				System.out.println(track);
				compareTo = current;
				track = 1;
				if (compareTo.equals(LocalDate.MAX))
					System.out.print("No Expiry: ");
				else
					System.out.print(compareTo + ": ");

			}
		}
		System.out.println(track);
		System.out.println();
	}

	/**
	 * Go through the linked list and remove all the items that are expired.
	 * 
	 * @param todaysDate - Date passed in so that we can simulate scenarios
	 */
	public void removeExpiredItems(LocalDate todaysDate) {
		while (!expdate.isEmpty() && expdate.peek().compareTo(LocalDate.MAX) != 0
				&& expdate.peek().isBefore(todaysDate)) {
			expdate.remove();
			itemQuantityInStock--;
		}
	}

	public String toString() {
		return item.toString() + " qty: " + itemQuantityInStock;
	}

	/**
	 * Update the quanity stored in the food item
	 * 
	 * @param scanner   - Input device to use
	 * @param buyOrSell - If we are to add to quantity (<code>true</code>) or remove
	 *                  (<code>false</code>)
	 * @return <code>true</code> if update was successfully, <code>false</code>
	 *         otherwise
	 */

	public boolean updateQuantity(Scanner scanner, int amount) {
		// If you are removing stock, then check that we have enough stock
		if (amount < 0 && itemQuantityInStock < (amount * -1)) {
			return false;
		}
		// Add items
		if (amount > 0) {
			// Input the expiry date
			boolean valid = false;
			LocalDate expiry = null;
			while (!valid) {
				System.out.print("Enter the expiry date of the item (yyyy-mm-dd or none): ");
				if (scanner.hasNext()) {
					String tempDate = scanner.next();
					try {
						if (tempDate.equalsIgnoreCase("none"))
							expiry = LocalDate.MAX;
						else
							expiry = LocalDate.parse(tempDate);
						valid = true;
					} catch (DateTimeParseException e) {
						System.out.println("Could not create date from input, please use format yyyy-mm-dd");
						System.out.println(e.getLocalizedMessage());
						valid = false;
					}
				} else {
					System.out.println("Invalid input");
					scanner.next();
					valid = false;
				}
			}

			for (int i = 0; i < amount; i++)
				expdate.add(expiry);
		} else {
			for (int i = 0; i < (amount * -1); i++)
				expdate.remove();
		}
		itemQuantityInStock += amount;
		return true;
	}
}
