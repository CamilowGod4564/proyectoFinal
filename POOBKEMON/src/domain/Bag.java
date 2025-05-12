package domain;
import java.util.*;

public class Bag {
    private TreeMap<String, Integer> itemCounts; 
    private TreeMap<String, Item> itemTemplates; 

    public Bag() {
        itemCounts = new TreeMap<>();
        itemTemplates = new TreeMap<>();
    }

    
    public void addItem(Item item) {
        String name = item.getName().toUpperCase();
        itemCounts.put(name, itemCounts.getOrDefault(name, 0) + 1);
        itemTemplates.put(name, item); 
    }

    public void delItem(String itemName) {
        String name = itemName.toUpperCase();
        if (itemCounts.containsKey(name)) {
            int count = itemCounts.get(name);
            if (count > 1) {
                itemCounts.put(name, count - 1);
            } else {
                itemCounts.remove(name);
                itemTemplates.remove(name);
            }
        }
    }

    public int getItemQuantity(String itemName) {
        return itemCounts.getOrDefault(itemName.toUpperCase(), 0);
    }


    public void useItem(String itemName, Pokemon pokemon) {
        String name = itemName.toUpperCase();
        if (getItemQuantity(name) > 0) {
            itemTemplates.get(name).use(pokemon);
            delItem(name); 
        }
    }
    
    public TreeMap<String, Item> getItems(){
    	return itemTemplates;
    }
}
