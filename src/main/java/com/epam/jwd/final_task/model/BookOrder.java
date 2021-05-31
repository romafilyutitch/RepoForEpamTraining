package com.epam.jwd.final_task.model;

import java.time.LocalDate;
import java.util.Objects;

public class BookOrder implements DbEntity {
    private  Long id;
    private final LibraryUser user;
    private final Book book;
    private final LocalDate orderDate;

    public BookOrder(LibraryUser user, Book book, LocalDate orderDate) {
        this.user = user;
        this.book = book;
        this.orderDate = orderDate;
    }

    @Override
    public Long getId() {
        return id;
    }

    public LibraryUser getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    @Override
    public void setId(Long id) {
        if (this.getId() != null) {
            throw new IllegalStateException("id already assigned");
        }
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookOrder bookOrder = (BookOrder) o;
        return Objects.equals(id, bookOrder.id) && Objects.equals(user, bookOrder.user) && Objects.equals(book, bookOrder.book) && Objects.equals(orderDate, bookOrder.orderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, book, orderDate);
    }

    @Override
    public String toString() {
        return "BookOrder{" +
                "id=" + id +
                ", user=" + user +
                ", book=" + book +
                ", orderDate=" + orderDate +
                '}';
    }
}
