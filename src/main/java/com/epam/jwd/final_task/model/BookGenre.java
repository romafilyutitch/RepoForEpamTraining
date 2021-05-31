package com.epam.jwd.final_task.model;

import java.util.Objects;

public class BookGenre implements DbEntity {
    private  Long id;
    private final String name;

    public BookGenre(String name) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookGenre bookGenre = (BookGenre) o;
        return Objects.equals(id, bookGenre.id) && Objects.equals(name, bookGenre.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "BookGenre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
