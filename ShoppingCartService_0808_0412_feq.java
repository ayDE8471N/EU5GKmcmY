// 代码生成时间: 2025-08-08 04:12:23
package com.example.cart;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * ShoppingCartService.java - A Struts2 action class for shopping cart functionality.
 *
 * This class provides methods to add items to the cart, remove items from the cart,
 * and retrieve the current state of the cart.
 */
@Namespace("/cart")
public class ShoppingCartService extends ActionSupport {

    /*
     * A map to simulate a shopping cart where the key is the product ID
     * and the value is the product quantity.
     */
    private Map<Integer, Integer> cart = new HashMap<>();

    /*
     * Adds a product to the cart.
     *
     * @param productId The ID of the product to add.
     * @param quantity The quantity of the product to add.
     * @return The result of the action.
     */
    public String addItem() {
        Integer productId = getProductId();
        Integer quantity = getQuantity();

        if (productId == null || quantity == null || quantity <= 0) {
            addActionError("Product ID or quantity is invalid.");
            return ERROR;
        }

        cart.put(productId, cart.getOrDefault(productId, 0) + quantity);
        return SUCCESS;
    }

    /*
     * Removes a product from the cart.
     *
     * @param productId The ID of the product to remove.
     * @return The result of the action.
     */
    public String removeItem() {
        Integer productId = getProductId();

        if (productId == null) {
            addActionError("Product ID is invalid.");
            return ERROR;
        }

        cart.entrySet().removeIf(entry -> entry.getKey().equals(productId));
        return SUCCESS;
    }

    /*
     * Retrieves the current state of the cart.
     *
     * @return A list of products in the cart.
     */
    public List<Map<String, Object>> getCartContents() {
        List<Map<String, Object>> cartContents = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
            Map<String, Object> productInfo = new HashMap<>();
            productInfo.put("productId", entry.getKey());
            productInfo.put("quantity", entry.getValue());
            cartContents.add(productInfo);
        }
        return cartContents;
    }

    /*
     * Getters and Setters
     */
    public Integer getProductId() {
        return (Integer) getParam("productId");
    }

    public void setProductId(Integer productId) {
        param.put("productId", productId);
    }

    public Integer getQuantity() {
        return (Integer) getParam("quantity");
    }

    public void setQuantity(Integer quantity) {
        param.put("quantity", quantity);
    }

    /*
     * Struts2 results
     */
    @Action(value = "addItem", results = {
        @Result(name = SUCCESS, type = "json")
    })
    public String addItemAction() {
        return addItem();
    }

    @Action(value = "removeItem", results = {
        @Result(name = SUCCESS, type = "json")
    })
    public String removeItemAction() {
        return removeItem();
    }

    @Action(value = "getCartContents", results = {
        @Result(name = SUCCESS, type = "json")
    })
    public String getCartContentsAction() {
        return SUCCESS;
    }
}
