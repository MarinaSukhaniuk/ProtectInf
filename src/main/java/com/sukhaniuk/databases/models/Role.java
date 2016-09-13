package com.sukhaniuk.databases.models;

/**
 * Created by shyslav on 9/13/16.
 */
public class Role {
    private int id;
    private int name;
    private int permission;

    public Role(int id, int name, int permission) {
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

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }
}
