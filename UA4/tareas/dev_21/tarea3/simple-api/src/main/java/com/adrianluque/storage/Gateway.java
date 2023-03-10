package com.adrianluque.storage;

import java.sql.Statement;

import com.adrianluque.storage.users.UserStorage;


public class Gateway {
    public UserStorage storage = new UserStorage();

    public Gateway() {
    }

}
