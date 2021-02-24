package com.gemini.util.components.table.model;

import com.gemini.util.components.table.TableColumn;

import java.util.Objects;

public class UserData extends TableData {

    @TableColumn(idx = 1)
    private String name;
    @TableColumn(idx = 2)
    private String category;
    @TableColumn(idx = 3)
    private String username;
    @TableColumn(idx = 4)
    private String lastLogin;
    @TableColumn(idx = 5)
    private String status;

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getUsername() {
        return username;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return Objects.equals(name, userData.name) &&
                Objects.equals(category, userData.category) &&
                Objects.equals(username, userData.username) &&
                Objects.equals(lastLogin, userData.lastLogin) &&
                Objects.equals(status, userData.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category, username, lastLogin, status);
    }
}
