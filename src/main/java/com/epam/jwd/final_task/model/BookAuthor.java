package com.epam.jwd.final_task.model;

import java.util.Objects;

public class BookAuthor implements DbEntity {
    private Long id;
    private final String name;

    public BookAuthor(String name) {
        this.name = name;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public void setId(Long id) {
        if (this.getId() != null) {
            throw new IllegalStateException("id already assigned");
        }
        this.id = id;
    }

    public BookAuthor updateName(String name) {
        if (name == null) {
            throw new NullPointerException();
        }
        final BookAuthor newBookAuthor = new BookAuthor(name);
        newBookAuthor.setId(id);
        return newBookAuthor;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookAuthor that = (BookAuthor) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "BookAuthor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
