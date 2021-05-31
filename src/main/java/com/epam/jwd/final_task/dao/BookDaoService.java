package com.epam.jwd.final_task.dao;

import com.epam.jwd.final_task.exception.DAOException;
import com.epam.jwd.final_task.model.Book;
import com.epam.jwd.final_task.model.BookAuthor;
import com.epam.jwd.final_task.model.BookGenre;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookDaoService extends AbstractDao<Book> implements BookDao {
    private BookAuthorDao authorDao = new BookAuthorDao();
    private BookGenreDao genreDao = new BookGenreDao();

    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String AUTHOR_COLUMN = "author";
    private static final String GENRE_COLUMN = "genre";
    private static final String YEAR_COLUMN = "year";
    private static final String PAGES_AMOUNT_COLUMN = "pages_amount";
    private static final String DESCRIPTION_COLUMN = "description";
    private static final String SAVE_PREPARED_SQL = "insert into lib_book (name, author, genre, year, pages_amount, description) values (?, ?, ?, ?, ?, ?)";
    private static final String FIND_ALL_SQL = "select id, name, author, genre, year, pages_amount, description from lib_book";
    private static final String UPDATE_PREPARED_SQL = "update lib_book set name = ?, author = ?, genre = ?, year = ?, pages_amount = ?, description = ? where id = ?";
    private static final String DELETE_PREPARED_SQL = "delete from lib_book where id = ?";

    public BookDaoService() {
        super(FIND_ALL_SQL, SAVE_PREPARED_SQL, UPDATE_PREPARED_SQL, DELETE_PREPARED_SQL);
    }
    @Override
    protected Book mapResultSet(ResultSet result) throws SQLException, DAOException {
        Optional<BookAuthor> optionalAuthor = authorDao.findById(result.getLong(AUTHOR_COLUMN));
        Optional<BookGenre> optionalBookGenre = genreDao.findById(result.getLong(GENRE_COLUMN));
        final Long id = result.getLong(ID_COLUMN);
        final String name = result.getString(NAME_COLUMN);
        final LocalDate date = result.getDate(YEAR_COLUMN).toLocalDate();
        final int pagesAmount = result.getInt(PAGES_AMOUNT_COLUMN);
        final String description = result.getString(DESCRIPTION_COLUMN);
        final Book bookFromResultSet = new Book(name, optionalAuthor.orElseThrow(DAOException::new), optionalBookGenre.orElseThrow(DAOException::new), date, pagesAmount, description);
        bookFromResultSet.setId(id);
        return bookFromResultSet;
    }

    @Override
    protected void setSavePrepareStatementValues(Book entity, PreparedStatement savePreparedStatement) throws SQLException, DAOException {
        savePreparedStatement.setString(1, entity.getName());
        savePreparedStatement.setLong(2, authorDao.save(entity.getAuthor()).getId());
        savePreparedStatement.setLong(3, genreDao.save(entity.getGenre()).getId());
        savePreparedStatement.setDate(4, Date.valueOf(entity.getDate()));
        savePreparedStatement.setInt(5, entity.getPagesAmount());
        savePreparedStatement.setString(6, entity.getDescription());
    }

    @Override
    protected void setUpdatePreparedStatementValues(Book entity, PreparedStatement updatePreparedStatement) throws SQLException, DAOException {
        updatePreparedStatement.setString(1, entity.getName());
        updatePreparedStatement.setLong(2, entity.getAuthor().getId());
        updatePreparedStatement.setLong(3, entity.getGenre().getId());
        updatePreparedStatement.setDate(4, Date.valueOf(entity.getDate()));
        updatePreparedStatement.setInt(5, entity.getPagesAmount());
        updatePreparedStatement.setString(6, entity.getDescription());
        updatePreparedStatement.setLong(7, entity.getId());
    }

    @Override
    public List<Book> findBooksByName(String name) throws DAOException {
        return findAll().stream().filter(book -> book.getName().equals(name)).collect(Collectors.toList());
    }

    @Override
    public List<Book> findBooksByAuthorName(String authorName) throws DAOException {
        return findAll().stream().filter(book -> book.getAuthor().getName().equals(authorName)).collect(Collectors.toList());
    }

    @Override
    public List<Book> findBooksByGenreName(String genreName) throws DAOException {
        return findAll().stream().filter(book -> book.getGenre().getName().equals(genreName)).collect(Collectors.toList());
    }

    @Override
    public List<Book> findBooksByYear(int year) throws DAOException {
        return findAll().stream().filter(book -> book.getDate().getYear() == year).collect(Collectors.toList());
    }
}
