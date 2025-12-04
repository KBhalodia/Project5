package com.example.rucafe;

import java.util.ArrayList;

import main.MenuItem;
import main.Order;
import main.StoreOrders;

/**
 * Singleton used to share current order and all store orders
 * between Activities.
 */
public class OrderManager {

    private static OrderManager instance;
    private Order currentOrder;
    private StoreOrders storeOrders;

    private OrderManager() {
        this.currentOrder = new Order();
        this.storeOrders = new StoreOrders(new ArrayList<Order>());
    }

    public static synchronized OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public StoreOrders getStoreOrders() {
        return storeOrders;
    }

    public void addToCurrentOrder(MenuItem item) {
        currentOrder.addItem(item);
    }

    public void removeFromCurrentOrder(MenuItem item) {
        currentOrder.removeItem(item);
    }

    public ArrayList<MenuItem> getCurrentItems() {
        if (currentOrder == null) {
            currentOrder = new Order();
        }
        return (ArrayList<MenuItem>) currentOrder.getItems();
    }

    public double getCurrentSubtotal() {
        if (currentOrder == null) {
            currentOrder = new Order();
        }
        return currentOrder.getSubtotal();
    }
    public void placeCurrentOrder() {
        if (currentOrder == null || currentOrder.getItems().isEmpty()) {
            return;
        }
        storeOrders.addOrder(currentOrder);
        // start a new current order
        currentOrder = new Order();
    }
}