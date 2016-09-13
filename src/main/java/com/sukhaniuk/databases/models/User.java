package com.sukhaniuk.databases.models;

public class User {
    private int id;
    private Role role;
    private String login;
    private String password;

    public User(int id, Role role, String login, String password) {
        this.id = id;
        this.role = role;
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean checkPass(String password){
        return password.equals(password);
    }
}
