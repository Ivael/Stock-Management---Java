import java.util.Comparator;

/**
 * 
 * @author This class contains the behaviour of an inventory Student Number
 *         version 2.0
 */
public class InventoryItemComparator implements Comparator<InventoryItem> {

	/*
	 * Compares given two objects
	 */
	@Override
	public int compare(InventoryItem obj1, InventoryItem obj2) {
		// Compare by item code
		return obj1.getItemCode() - obj2.getItemCode();
	}

}