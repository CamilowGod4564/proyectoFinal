package domain;
import java.util.*;

public class Bag {
	private TreeMap<String,Item> items;
	/**
	 * @param items
	 */
	public Bag() {
		items = new TreeMap<>();
	}
	
	public void useItem(String itemName,Pokemon pokemon) {
		items.get(itemName.toUpperCase()).use(pokemon);
	}
	public void addItem(Item item) {
		items.put(item.getName(), item);
	}
	public void delItem(String itemName) {
		items.remove(itemName.toUpperCase());
	}
	//falta get items
	
}
