package com.ecommerce.repository;

import com.ecommerce.model.Order;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileOrderRepository implements OrderRepository {
    private final String filePath = "orders.ser";
    private List<Order> orders;

    public FileOrderRepository() {
        this.orders = loadOrdersFromFile();
    }

    @SuppressWarnings("unchecked")
    private List<Order> loadOrdersFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<Order>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void saveOrdersToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(orders);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Order order) {
        orders.add(order);
        saveOrdersToFile();
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(orders);
    }
}