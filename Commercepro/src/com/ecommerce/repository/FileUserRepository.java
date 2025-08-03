package com.ecommerce.repository;

import com.ecommerce.model.User;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileUserRepository implements UserRepository {
    private static final String FILE_PATH = "users.ser";
    private Map<String, User> users;

    public FileUserRepository() {
        this.users = loadUsersFromFile();
    }

    @SuppressWarnings("unchecked")
    private Map<String, User> loadUsersFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (Map<String, User>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new HashMap<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    public void saveUsersToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findByUsername(String username) {
        return users.get(username);
    }

    @Override
    public void save(User user) {
        users.put(user.getUsername(), user);
        saveUsersToFile();
    }
}