package com.epam.jwd.final_task.model;

import java.util.Objects;

public class LibraryUser implements DbEntity {
    private final Long id;
    private final String login;
    private final String password;
    private final UserRole role;
    private final Subscription userSubscription;

    public LibraryUser(Long id, String login, String password, UserRole role, Subscription subscription) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.userSubscription = subscription;
    }

    public LibraryUser(String login, String password) {
        this(null, login, password, UserRole.READER, null);
    }

    public LibraryUser(String login, String password, UserRole role) {
        this(null, login, password, role, null);
    }

    @Override
    public Long getId() {
        return id;
    }


    public String getLogin() {
        return login;
    }


    public String getPassword() {
        return password;
    }


    public UserRole getRole() {
        return role;
    }


    public Subscription getUserSubscription() {
        return userSubscription;
    }

    public LibraryUser updateLogin(String newLogin) {
        if (newLogin == null) {
            return this;
        }
        return new LibraryUser(id, newLogin, password, role, userSubscription);
    }

    public LibraryUser updatePassword(String newPassword) {
        if (newPassword == null) {
            return this;
        }
        return new LibraryUser(id, login, newPassword, role, userSubscription);
    }

    public LibraryUser updateRole(UserRole newRole) {
        if (newRole == null) {
            return this;
        }
        return new LibraryUser(id, login, password, newRole, userSubscription);
    }

    public LibraryUser updateSubscription(Subscription newSubscription) {
        if (newSubscription == null) {
            return this;
        }
        return new LibraryUser(id, login, password, role, newSubscription);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibraryUser that = (LibraryUser) o;
        return Objects.equals(id, that.id) && Objects.equals(login, that.login) && Objects.equals(password, that.password) && role == that.role && Objects.equals(userSubscription, that.userSubscription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, role, userSubscription);
    }

    @Override
    public String toString() {
        return "LibraryUser{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", userSubscription=" + userSubscription +
                '}';
    }
}
