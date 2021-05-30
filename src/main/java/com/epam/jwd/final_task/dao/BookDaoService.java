package com.epam.jwd.final_task.dao;

import com.epam.jwd.final_task.connectionPool.ConnectionPool;
import com.epam.jwd.final_task.exception.DAOException;
import com.epam.jwd.final_task.model.Book;
import com.epam.jwd.final_task.model.BookAuthor;
import com.epam.jwd.final_task.model.BookGenre;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookDaoService implements BookDao {
    private BookAuthorDao authorDao = new BookAuthorDao();
    private BookGenreDao genreDao = new BookGenreDao();

    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String AUTHOR_COLUMN = "author";
    private static final String GENRE_COLUMN = "genre";
    private static final String YEAR_COLUMN = "year";
    private static final String PAGES_AMOUNT_COLUMN = "pages_amount";
    private static final String DESCRIPTION_COLUMN = "description";
    private static final String GENERATED_KEY_COLUMN = "GENERATED_KEY";
    private static final String SAVE_PREPARED_SQL = "insert into lib_book (name, author, genre, year, pages_amount, description) values (?, ?, ?, ?, ?, ?)";
    private static final String FIND_ALL_SQL = "select id, name, author, genre, year, pages_amount, description from lib_book";
    private static final String UPDATE_PREPARED_SQL = "update lib_book set name = ?, author = ?, genre = ?, year = ?, pages_amount = ?, description = ? where id = ?";
    private static final String DELETE_PREPARED_SQL = "delete from lib_book where id = ?";


    @Override
    public List<Book> findBooksByName(String name) throws DAOException {
        return findAll().stream().filter(book -> book.getName().equals(name)).collect(Collectors.toList());
    }

    @Override
    public List<Book> findBooksByAuthor(String author) throws DAOException {
        return findAll().stream().filter(book -> book.getAuthor().getName().equals(author)).collect(Collectors.toList());
    }

    @Override
    public List<Book> findBooksByGenre(String genre) throws DAOException {
        return findAll().stream().filter(book -> book.getGenre().getName().equals(genre)).collect(Collectors.toList());
    }

    @Override
    public List<Book> findBooksByYear(int year) throws DAOException {
        return findAll().stream().filter(book -> book.getDate().getYear() == year).collect(Collectors.toList());
    }


    @Override
    public Book save(Book entity) throws DAOException {
        try (Connection connection = ConnectionPool.getConnectionPool().takeFreeConnection();
             PreparedStatement saveStatement = connection.prepareStatement(SAVE_PREPARED_SQL, Statement.RETURN_GENERATED_KEYS)) {
            saveStatement.setString(1, entity.getName());
            BookAuthor savedAuthor = authorDao.save(entity.getAuthor());
            saveStatement.setLong(2, savedAuthor.getId());
            BookGenre savedGenre = genreDao.save(entity.getGenre());
            saveStatement.setLong(3, savedGenre.getId());
            saveStatement.setDate(4, Date.valueOf(entity.getDate()));
            saveStatement.setInt(5, entity.getPagesAmount());
            saveStatement.setString(6, entity.getDescription());
            saveStatement.executeUpdate();
            ResultSet result = saveStatement.getGeneratedKeys();
            result.next();
            Long id = result.getLong(GENERATED_KEY_COLUMN);
            return new Book(id, entity.getName(), savedAuthor, savedGenre, entity.getDate(), entity.getPagesAmount(), entity.getDescription());
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<Book> findAll() throws DAOException {
        List<Book> books = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnectionPool().takeFreeConnection();
             Statement findAllStatement = connection.createStatement();
             ResultSet result = findAllStatement.executeQuery(FIND_ALL_SQL)) {
            while (result.next()) {
                Optional<BookAuthor> author = authorDao.findById(result.getLong(AUTHOR_COLUMN));
                Optional<BookGenre> genre = genreDao.findById(result.getLong(GENRE_COLUMN));
                Long id = result.getLong(ID_COLUMN);
                String name = result.getString(NAME_COLUMN);
                LocalDate year = result.getDate(YEAR_COLUMN).toLocalDate();
                int pagesAmount = result.getInt(PAGES_AMOUNT_COLUMN);
                String description = result.getString(DESCRIPTION_COLUMN);
                Book book = new Book(id, name, author.orElseThrow(DAOException::new), genre.orElseThrow(DAOException::new), year, pagesAmount, description);
                books.add(book);
            }
            return books;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Optional<Book> findById(Long id) throws DAOException {
        return findAll().stream().filter(book -> book.getId().equals(id)).findAny();
    }

    @Override
    public Book update(Book entity) throws DAOException {
        try (Connection connection = ConnectionPool.getConnectionPool().takeFreeConnection();
             PreparedStatement updateStatement = connection.prepareStatement(UPDATE_PREPARED_SQL)) {
            updateStatement.setString(1, entity.getName());
            updateStatement.setLong(2, entity.getAuthor().getId());
            updateStatement.setLong(3, entity.getGenre().getId());
            updateStatement.setInt(4, entity.getPagesAmount());
            updateStatement.setInt(5, entity.getPagesAmount());
            updateStatement.setString(6, entity.getDescription());
            updateStatement.setLong(7, entity.getId());
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return entity;
    }

    @Override
    public void delete(Long id) throws DAOException {
        try (Connection connection = ConnectionPool.getConnectionPool().takeFreeConnection();
             PreparedStatement deleteStatement = connection.prepareStatement(DELETE_PREPARED_SQL)) {
            deleteStatement.setLong(1, id);
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
