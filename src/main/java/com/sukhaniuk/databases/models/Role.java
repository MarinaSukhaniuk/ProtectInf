package com.sukhaniuk.databases.models;

public class Role {
    private int id;
    private String name;
    private int permission;

    public Role(int id, String name, int permission) {
        this.id = id;
        this.name = name;
        this.permission = permission;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }
}
