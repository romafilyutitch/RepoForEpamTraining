package com.epam.jwd.final_task.model;

import java.time.LocalDate;
import java.util.Objects;

public class Book implements DbEntity {
    private final Long id;
    private final String name;
    private final BookAuthor author;
    private final BookGenre genre;
    private final LocalDate date;
    private final int pagesAmount;
    private final String description;

    public Book(Long id, String name, BookAuthor author, BookGenre genre, LocalDate date, int pagesAmount, String description) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.date = date;
        this.pagesAmount = pagesAmount;
        this.description = description;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BookAuthor getAuthor() {
        return author;
    }

    public BookGenre getGenre() {
        return genre;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getPagesAmount() {
        return pagesAmount;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return pagesAmount == book.pagesAmount && Objects.equals(id, book.id) && Objects.equals(name, book.name) && Objects.equals(author, book.author) && Objects.equals(genre, book.genre) && Objects.equals(date, book.date) && Objects.equals(description, book.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, author, genre, date, pagesAmount, description);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", date=" + date +
                ", pagesAmount=" + pagesAmount +
                ", description='" + description + '\'' +
                '}';
    }
}
