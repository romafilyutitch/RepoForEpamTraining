package com.epam.jwd.final_task.dao;

import com.epam.jwd.final_task.connectionPool.ConnectionPool;
import com.epam.jwd.final_task.exception.DAOException;
import com.epam.jwd.final_task.model.Book;
import com.epam.jwd.final_task.model.BookOrder;
import com.epam.jwd.final_task.model.LibraryUser;

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

public class OrderDaoService implements OrderDao {
    private final BookDaoService bookDaoService = new BookDaoService();
    private final UserDaoService userDaoService = new UserDaoService();

    private static final String ID_COLUMN = "id";
    private static final String READER_COLUMN = "reader";
    private static final String BOOK_COLUMN = "book";
    private static final String ORDER_DATE_COLUMN = "order_date";
    private static final String GENERATED_KEY_COLUMN = "GENERATED_KEY";
    private static final String SAVE_PREPARED_SQL = "insert into book_order (reader, book, order_date) values (?, ?, ?)";
    private static final String FIND_ALL_SQL = "select id, reader, book, order_date from book_order";
    private static final String UPDATE_PREPARED_SQL = "update book_order set reader = ?, book = ?, order_date = ? where id = ?";
    private static final String DELETE_PREPARED_SQL = "delete from book_order where id = ?";

    @Override
    public BookOrder save(BookOrder entity) throws DAOException {
        try (Connection connection = ConnectionPool.getConnectionPool().takeFreeConnection();
             PreparedStatement saveStatement = connection.prepareStatement(SAVE_PREPARED_SQL, Statement.RETURN_GENERATED_KEYS)) {
            saveStatement.setLong(1, entity.getUser().getId());
            saveStatement.setLong(2, entity.getBook().getId());
            saveStatement.setDate(3, Date.valueOf(entity.getOrderDate()));
            saveStatement.executeUpdate();
            ResultSet result = saveStatement.getGeneratedKeys();
            result.next();
            Long id = result.getLong(GENERATED_KEY_COLUMN);
            return new BookOrder(id, entity.getUser(), entity.getBook(), entity.getOrderDate());
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<BookOrder> findAll() throws DAOException {
        List<BookOrder> orders = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnectionPool().takeFreeConnection();
             Statement findAllStatement = connection.createStatement();
             ResultSet resultSet = findAllStatement.executeQuery(FIND_ALL_SQL)) {
            while (resultSet.next()) {
                Optional<LibraryUser> reader = userDaoService.findById(resultSet.getLong(READER_COLUMN));
                Optional<Book> book = bookDaoService.findById(resultSet.getLong(BOOK_COLUMN));
                BookOrder order = new BookOrder(resultSet.getLong(ID_COLUMN), reader.orElseThrow(DAOException::new), book.orElseThrow(DAOException::new), resultSet.getDate(ORDER_DATE_COLUMN).toLocalDate());
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Optional<BookOrder> findById(Long id) throws DAOException {
        return findAll().stream().filter(bookOrder -> bookOrder.getId().equals(id)).findAny();
    }

    @Override
    public BookOrder update(BookOrder entity) throws DAOException {
        try (Connection connection = ConnectionPool.getConnectionPool().takeFreeConnection();
             PreparedStatement updateStatement = connection.prepareStatement(UPDATE_PREPARED_SQL)) {
            updateStatement.setLong(1, entity.getUser().getId());
            updateStatement.setLong(2, entity.getBook().getId());
            updateStatement.setDate(3, Date.valueOf(entity.getOrderDate()));
            updateStatement.setLong(4, entity.getId());
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

    @Override
    public List<BookOrder> findOrdersByUser(LibraryUser user) throws DAOException {
        return findAll().stream().filter(bookOrder -> bookOrder.getUser().getId().equals(user.getId())).collect(Collectors.toList());
    }

    @Override
    public List<BookOrder> findOrdersByBook(Book book) throws DAOException {
        return findAll().stream().filter(bookOrder -> bookOrder.getBook().getName().equals(book.getName())).collect(Collectors.toList());
    }

    @Override
    public List<BookOrder> findOrdersByOrderDate(LocalDate orderDate) throws DAOException {
        return findAll().stream().filter(bookOrder -> bookOrder.getOrderDate().equals(orderDate)).collect(Collectors.toList());
    }
}
