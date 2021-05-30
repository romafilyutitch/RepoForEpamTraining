package com.epam.jwd.final_task.dao;

import com.epam.jwd.final_task.connectionPool.ConnectionPool;
import com.epam.jwd.final_task.exception.DAOException;
import com.epam.jwd.final_task.model.BookAuthor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookAuthorDao implements Dao<BookAuthor> {
    private static final String SAVE_PREPARED_SQL = "insert into book_author (name) values (?)";
    private static final String FIND_ALL_PREPARED_SQL = "select id, name from book_author";
    private static final String UPDATE_PREPARED_SQL = "update book_author set name = ? where id = ?";
    private static final String DELETE_PREPARED_SQL = "delete from book_author where id = ?";
    public static final String GENERATED_KEY_COLUMN = "GENERATED_KEY";
    public static final String ID_COLUMN = "id";
    public static final String NAME_COLUMN = "name";

    @Override
    public BookAuthor save(BookAuthor entity) throws DAOException {
        Optional<BookAuthor> optionalBookAuthor = findAll().stream().filter(author -> author.getName().equals(entity.getName())).findAny();
        if (optionalBookAuthor.isPresent()) {
            return optionalBookAuthor.get();
        }
        try (Connection connection = ConnectionPool.getConnectionPool().takeFreeConnection();
             PreparedStatement saveStatement = connection.prepareStatement(SAVE_PREPARED_SQL, Statement.RETURN_GENERATED_KEYS)) {
            saveStatement.setString(1, entity.getName());
            saveStatement.executeUpdate();
            ResultSet resultSet = saveStatement.getGeneratedKeys();
            resultSet.next();
            Long id = resultSet.getLong(GENERATED_KEY_COLUMN);
            return new BookAuthor(id, entity.getName());
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<BookAuthor> findAll() throws DAOException {
        List<BookAuthor> authors = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnectionPool().takeFreeConnection();
             PreparedStatement findAllStatement = connection.prepareStatement(FIND_ALL_PREPARED_SQL);
             ResultSet resultSet = findAllStatement.executeQuery()) {
            while (resultSet.next()) {
                BookAuthor author = new BookAuthor(resultSet.getLong(ID_COLUMN), resultSet.getString(NAME_COLUMN));
                authors.add(author);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return authors;
    }

    @Override
    public Optional<BookAuthor> findById(Long id) throws DAOException {
        return findAll().stream().filter(bookAuthor -> bookAuthor.getId().equals(id)).findAny();
    }

    @Override
    public BookAuthor update(BookAuthor entity) throws DAOException {
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
