package com.epam.jwd.final_task.model;

import java.time.LocalDate;
import java.util.Objects;

public class Book implements DbEntity {
    private Long id;
    private final String name;
    private final BookAuthor author;
    private final BookGenre genre;
    private final LocalDate date;
    private final int pagesAmount;
    private final String description;

    public Book(String name, BookAuthor author, BookGenre genre, LocalDate date, int pagesAmount, String description) {
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
    public void setId(Long id) {
        if (this.getId() != null) {
            throw new IllegalStateException("id already assigned");
        }
        this.id = id;
    }

    public Book updateName(String newName) {
        if (newName == null) {
            throw new NullPointerException();
        }
        final Book updatedBook = new Book(newName, author, genre, date, pagesAmount, description);
        updatedBook.setId(id);
        return updatedBook;
    }

    public Book updateAuthor(BookAuthor newAuthor) {
        if (newAuthor == null) {
            throw new NullPointerException();
        }
        final Book updatedBook = new Book(name, newAuthor, genre, date, pagesAmount, description);
        updatedBook.setId(id);
        return updatedBook;
    }

    public Book updateGenre(BookGenre newGenre) {
        if (newGenre == null) {
            throw new NullPointerException();
        }
        final Book updatedBook = new Book(name, author, newGenre, date, pagesAmount, description);
        updatedBook.setId(id);
        return updatedBook;
    }

    public Book updateDate(LocalDate newDate) {
        if (newDate == null) {
            throw new NullPointerException();
        }
        final Book updatedBook = new Book(name, author, genre, newDate, pagesAmount, description);
        updatedBook.setId(id);
        return updatedBook;
    }

    public Book updatePagesAmount(int newPagesAmount) {
        final Book updatedBook = new Book(name, author, genre, date, newPagesAmount, description);
        updatedBook.setId(id);
        return updatedBook;
    }

    public Book updateDescription(String newDescription) {
        if (newDescription == null) {
            throw new NullPointerException();
        }
        final Book updatedBook = new Book(name, author, genre, date, pagesAmount, newDescription);
        updatedBook.setId(id);
        return updatedBook;
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
