package com.siam.storage.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.siam.enteties.Order;
import com.siam.storage.OrderStoringService;

public class DefaultOrderStoringService implements OrderStoringService {

    private static final String ORDERS_DATA_FILE_NAME = "orders.bin";
    private static final String CURRENT_TASK_RESOURCE_FOLDER = "finaltask";
    private static final String DATA_STORAGE_FOLDER = "data"; // This is for writable storage outside the classpath

    private static DefaultOrderStoringService instance;

    private DefaultOrderStoringService() {
    }

    @Override
    public void saveOrders(List<Order> orders) {
        try {
            // Ensure the directory exists
            Path directoryPath = Paths.get(DATA_STORAGE_FOLDER, CURRENT_TASK_RESOURCE_FOLDER);
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }

            // Save the orders
            try (var oos = new ObjectOutputStream(new FileOutputStream(
                    directoryPath.resolve(ORDERS_DATA_FILE_NAME).toFile()
            ))) {
                oos.writeObject(orders);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> loadOrders() {
        try (var ois = new ObjectInputStream(new FileInputStream(
                Paths.get(DATA_STORAGE_FOLDER, CURRENT_TASK_RESOURCE_FOLDER, ORDERS_DATA_FILE_NAME).toFile()
        ))) {
            return (List<Order>) ois.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    public static OrderStoringService getInstance() {
        if (instance == null) {
            instance = new DefaultOrderStoringService();
        }
        return instance;
    }
}
