package com.epam.jwd.final_task.dao;

import com.epam.jwd.final_task.connectionPool.ConnectionPool;
import com.epam.jwd.final_task.exception.DAOException;
import com.epam.jwd.final_task.model.BookGenre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookGenreDao implements Dao<BookGenre> {
    private static final String SAVE_PREPARED_SQL = "insert into book_genre (name) values (?)";
    private static final String FIND_ALL_SQL = "select id, name from book_genre";
    private static final String UPDATE_PREPARED_SQL = "update book_genre set name = ? where id = ?";
    private static final String DELETE_PREPARED_SQL = "delete from book_genre where id = ?";
    public static final String GENERATED_KEY_COLUMN = "GENERATED_KEY";
    public static final String ID_COLUMN = "id";
    public static final String NAME_COLUMN = "name";

    @Override
    public BookGenre save(BookGenre entity) throws DAOException {
        Optional<BookGenre> bookGenre = findAll().stream().filter(genre -> genre.getName().equals(entity.getName())).findAny();
        if (bookGenre.isPresent()) {
            return bookGenre.get();
        }
        try (Connection connection = ConnectionPool.getConnectionPool().takeFreeConnection();
             PreparedStatement saveStatement = connection.prepareStatement(SAVE_PREPARED_SQL, Statement.RETURN_GENERATED_KEYS)) {
            saveStatement.setString(1, entity.getName());
            saveStatement.executeUpdate();
            ResultSet generatedKeys = saveStatement.getGeneratedKeys();
            generatedKeys.next();
            Long id = generatedKeys.getLong(GENERATED_KEY_COLUMN);
            return new BookGenre(id, entity.getName());
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<BookGenre> findAll() throws DAOException {
        List<BookGenre> bookGenres = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnectionPool().takeFreeConnection();
        Statement findAllStatement = connection.createStatement();
             ResultSet resultSet = findAllStatement.executeQuery(FIND_ALL_SQL)) {
            while (resultSet.next()) {
                BookGenre bookGenre = new BookGenre(resultSet.getLong(ID_COLUMN), resultSet.getString(NAME_COLUMN));
                bookGenres.add(bookGenre);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return bookGenres;
    }

    @Override
    public Optional<BookGenre> findById(Long id) throws DAOException{
        return findAll().stream().filter(bookGenre -> bookGenre.getId().equals(id)).findAny();
    }

    @Override
    public BookGenre update(BookGenre entity) throws DAOException {
        try (Connection connection = ConnectionPool.getConnectionPool().takeFreeConnection();
        PreparedStatement updateStatement = connection.prepareStatement(UPDATE_PREPARED_SQL)) {
            updateStatement.setString(1, entity.getName());
            updateStatement.setLong(2, entity.getId());
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
