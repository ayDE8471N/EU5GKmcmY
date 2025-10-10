// 代码生成时间: 2025-10-10 21:41:46
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * ShoppingCart class to handle shopping cart functionality.
 * This class is a simple representation of a shopping cart.
 * It includes methods to add items to the cart, remove items,
 * update quantities, and list all items in the cart.
 */
public class ShoppingCart extends ActionForm {

    private List<CartItem> items;
    private double total;

    public ShoppingCart() {
        items = new ArrayList<CartItem>();
        total = 0.0;
    }

    /**
     * Adds an item to the shopping cart.
     * If the item already exists, it updates the quantity.
     * 
     * @param item The item to be added.
     */
    public void addItem(CartItem item) {
        for (CartItem existingItem : items) {
            if (existingItem.getId().equals(item.getId())) {
                existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
                return;
            }
        }
        items.add(item);
    }

    /**
     * Removes an item from the shopping cart.
     * 
     * @param id The ID of the item to be removed.
     */
    public void removeItem(String id) {
        items.removeIf(item -> item.getId().equals(id));
    }

    /**
     * Updates the quantity of a specific item in the cart.
     * 
     * @param id The ID of the item.
     * @param quantity The new quantity.
     */
    public void updateQuantity(String id, int quantity) {
        for (CartItem item : items) {
            if (item.getId().equals(id)) {
                item.setQuantity(quantity);
                break;
            }
        }
    }

    /**
     * Lists all items in the shopping cart.
     * 
     * @return A list of cart items.
     */
    public List<CartItem> listItems() {
        return items;
    }

    /**
     * Returns the total cost of all items in the cart.
     * 
     * @return The total cost.
     */
    public double getTotal() {
        double total = 0.0;
        for (CartItem item : items) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }

    // Getters and Setters
    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public double getTotalValue() {
        return total;
    }

    public void setTotalValue(double total) {
        this.total = total;
    }
}
