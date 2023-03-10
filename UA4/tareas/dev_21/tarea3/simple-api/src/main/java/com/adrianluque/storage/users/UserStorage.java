package com.adrianluque.storage.users;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserStorage {
    // Attributes
    int key = 0;
    private Map<Integer, User> userMap;

    // Constructor
    public UserStorage() {
        userMap = new HashMap<Integer, User>();
        userMap.put(1, new User(1, "ladrianK", "2013-04-06"));
        userMap.put(2, new User(2, "Viictor_2217_", "2017-01-31"));
        userMap.put(3, new User(3, "mmurillo23", "1999-11-24"));
        userMap.put(4, new User(4, "Paco", "1999-11-24"));
        key = 5;
    }

    // Find user index

    // Get Methods
    public User getUserByIndex(int key) {
        return this.userMap.get(key);
    }

    public User getUserByUUID(long uuid) {
        User user = null;
        for (User u : userMap.values()) {
            if (u.getUUID() == uuid) {
                user = u;
            }
        }
        return user;
    }

    public User getUserById(String id) {
        User user = null;
        for (User u : userMap.values()) {
            if (u.getId().toLowerCase().equals(id.toLowerCase())) {
                user = u;
            }
        }
        return user;
    }

    public List<User> getUsersByIdLike(String id) {
        List<User> users = new ArrayList<User>();
        for (User u : userMap.values()) {
            if (u.getId().toLowerCase().contains(id.toLowerCase())) {
                users.add(u);
            }
        }
        return users;
    }

    public List<User> getUsersByCreationDate(String creationDate) {
        List<User> users = new ArrayList<User>();
        for (User u : userMap.values()) {
            if (u.getCreationDate().equals(creationDate)) {
                users.add(u);
            }
        }
        return users;
    }

    public List<User> getUsersCreatedAfter(String date) throws Exception {
        List<User> users = new ArrayList<User>();

        long dateTime = new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime();

        for (User u : userMap.values()) {
            long iterableDateTime = new SimpleDateFormat("yyyy-MM-dd").parse(u.getCreationDate()).getTime();

            if (iterableDateTime > dateTime) {
                users.add(u);
            }
        }
        return users;
    }

    public List<User> getUsersCreatedBefore(String date) throws Exception {
        List<User> users = new ArrayList<User>();

        long dateTime = new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime();

        for (User u : userMap.values()) {
            long iterableDateTime = new SimpleDateFormat("yyyy-MM-dd").parse(u.getCreationDate()).getTime();

            if (iterableDateTime < dateTime) {
                users.add(u);
            }
        }
        return users;
    }

    public List<User> getUsersCreatedBetween(String after, String before) throws Exception {
        List<User> users = new ArrayList<User>();

        long dateTimeAfter = new SimpleDateFormat("yyyy-MM-dd").parse(before).getTime();
        long dateTimeBefore = new SimpleDateFormat("yyyy-MM-dd").parse(before).getTime();

        for (User u : userMap.values()) {
            long iterableDateTime = new SimpleDateFormat("yyyy-MM-dd").parse(u.getCreationDate()).getTime();

            if (iterableDateTime > dateTimeAfter && iterableDateTime < dateTimeBefore) {
                users.add(u);
            }
        }
        return users;
    }

    // Create user methods
    public boolean addUser(User user) {
        boolean added = false;
        User u = getUserByUUID(user.getUUID());
        if (u == null) {
            u = getUserById(user.getId());
            if (u == null) {
                userMap.put(key, user);
                key++;
                added = true;
            }
        }
        return added;
    }

    // Update user methods
    public boolean updateUser(int key, User user) {
        boolean updated = false;
        userMap.replace(key, user);
        if (getUserById(user.getId()) != null) {
            updated = true;
        }
        return updated;
    }

    // Delete user methods
    public boolean removeUser(int key) {
        boolean deleted = false;
        userMap.remove(key);
        if (getUserByIndex(key) == null) {
            deleted = true;
        }

        return deleted;
    }

    public boolean removeLastUser() {
        boolean deleted = false;
        userMap.remove(this.key);
        if (getUserByIndex(this.key) == null) {
            deleted = true;
        }
        return deleted;
    }
}