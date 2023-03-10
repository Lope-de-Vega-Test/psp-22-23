package com.adrianluque.storage.users;

public class User {
    // Attributes
    long uuid;
    String id;
    String creationDate;

    // Constructor
    public User() {}
    public User(long uuid, String id, String creationDate) {
        this.uuid = uuid;
        this.id = id;
        this.creationDate = creationDate;
    }
    // Getters
    public long getUUID() {
        return this.uuid;
    }

    public String getId() {
        return this.id;
    }

    public String getCreationDate() {
        return this.creationDate;
    }

    // Setters
    public void setUUID(long uuid) {
        this.uuid = uuid;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }


    
}
