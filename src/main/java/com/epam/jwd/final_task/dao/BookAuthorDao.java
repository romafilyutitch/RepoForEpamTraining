package com.epam.jwd.final_task.dao;

import com.epam.jwd.final_task.exception.DAOException;
import com.epam.jwd.final_task.model.BookAuthor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class BookAuthorDao extends AbstractDao<BookAuthor> {
    private static final String SAVE_PREPARED_SQL = "insert into book_author (name) values (?)";
    private static final String FIND_ALL_PREPARED_SQL = "select id, name from book_author";
    private static final String UPDATE_PREPARED_SQL = "update book_author set name = ? where id = ?";
    private static final String DELETE_PREPARED_SQL = "delete from book_author where id = ?";
    public static final String ID_COLUMN = "id";
    public static final String NAME_COLUMN = "name";

    public BookAuthorDao() {
        super(FIND_ALL_PREPARED_SQL, SAVE_PREPARED_SQL, UPDATE_PREPARED_SQL, DELETE_PREPARED_SQL);
    }

    @Override
    protected BookAuthor mapResultSet(ResultSet result) throws SQLException {
        final BookAuthor author = new BookAuthor(result.getString(NAME_COLUMN));
        author.setId(result.getLong(ID_COLUMN));
        return author;
    }

    @Override
    protected void setSavePrepareStatementValues(BookAuthor entity, PreparedStatement savePreparedStatement) throws SQLException {
        savePreparedStatement.setString(1, entity.getName());
    }

    @Override
    protected void setUpdatePreparedStatementValues(BookAuthor entity, PreparedStatement updatePreparedStatement) throws SQLException {
        updatePreparedStatement.setString(1, entity.getName());
        updatePreparedStatement.setLong(2, entity.getId());
    }

    @Override
    protected Optional<BookAuthor> findSaved(BookAuthor author) throws DAOException {
        return findAll().stream().filter(authorFromTable -> authorFromTable.getName().equalsIgnoreCase(author.getName())).findAny();
    }
}
