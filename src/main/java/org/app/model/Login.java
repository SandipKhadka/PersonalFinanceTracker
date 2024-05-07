package org.app.model;

public class Login {
    String userName, password;

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long hashedPassword() {
        return password.hashCode();
    }

}
