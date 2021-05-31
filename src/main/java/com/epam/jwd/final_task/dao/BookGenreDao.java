package com.epam.jwd.final_task.dao;

import com.epam.jwd.final_task.exception.DAOException;
import com.epam.jwd.final_task.model.BookGenre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class BookGenreDao extends AbstractDao<BookGenre> {
    private static final String SAVE_PREPARED_SQL = "insert into book_genre (name) values (?)";
    private static final String FIND_ALL_SQL = "select id, name from book_genre";
    private static final String UPDATE_PREPARED_SQL = "update book_genre set name = ? where id = ?";
    private static final String DELETE_PREPARED_SQL = "delete from book_genre where id = ?";
    public static final String ID_COLUMN = "id";
    public static final String NAME_COLUMN = "name";


    public BookGenreDao() {
        super(FIND_ALL_SQL, SAVE_PREPARED_SQL, UPDATE_PREPARED_SQL, DELETE_PREPARED_SQL);
    }

    @Override
    protected BookGenre mapResultSet(ResultSet result) throws SQLException {
        final BookGenre bookGenreFromTable = new BookGenre(result.getString(NAME_COLUMN));
        bookGenreFromTable.setId(result.getLong(ID_COLUMN));
        return bookGenreFromTable;
    }

    @Override
    protected void setSavePrepareStatementValues(BookGenre entity, PreparedStatement savePreparedStatement) throws SQLException {
        savePreparedStatement.setString(1, entity.getName());
    }

    @Override
    protected void setUpdatePreparedStatementValues(BookGenre entity, PreparedStatement updatePreparedStatement) throws SQLException {
        updatePreparedStatement.setString(1, entity.getName());
        updatePreparedStatement.setLong(2, entity.getId());
    }

    @Override
    public Optional<BookGenre> findSaved(BookGenre genre) throws DAOException {
        return findAll().stream().filter(genreFromTable -> genreFromTable.getName().equalsIgnoreCase(genre.getName())).findAny();
    }
}
