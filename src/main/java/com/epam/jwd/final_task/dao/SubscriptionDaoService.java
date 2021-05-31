package com.epam.jwd.final_task.dao;

import com.epam.jwd.final_task.model.Subscription;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubscriptionDaoService extends AbstractDao<Subscription> {

    private static final String SAVE_PREPARED_SQL = "insert into reader_subscription (start_date, end_date) values (?, ?)";
    private static final String FIND_ALL_SQL = "select id, start_date, end_date from reader_subscription";
    private static final String UPDATE_PREPARED_SQL = "update reader_subscription set start_date = ?, end_date = ? where id = ?";
    private static final String DELETE_PREPARED_SQL = "delete from reader_subscription where id = ?";
    public static final String ID_COLUMN = "id";
    public static final String START_DATE_COLUMN = "start_date";
    public static final String END_DATE_COLUMN = "end_date";

    public SubscriptionDaoService() {
       super(FIND_ALL_SQL, SAVE_PREPARED_SQL, UPDATE_PREPARED_SQL, DELETE_PREPARED_SQL);
    }

    @Override
    protected Subscription mapResultSet(ResultSet result) throws SQLException {
        final Subscription subscriptionFromResultSet = new Subscription(result.getDate(START_DATE_COLUMN).toLocalDate(), result.getDate(END_DATE_COLUMN).toLocalDate());
        subscriptionFromResultSet.setId(result.getLong(ID_COLUMN));
        return subscriptionFromResultSet;
    }

    @Override
    protected void setSavePrepareStatementValues(Subscription entity, PreparedStatement savePreparedStatement) throws SQLException {
        savePreparedStatement.setDate(1, Date.valueOf(entity.getStartDate()));
        savePreparedStatement.setDate(2, Date.valueOf(entity.getEndDate()));
    }

    @Override
    protected void setUpdatePreparedStatementValues(Subscription entity, PreparedStatement updatePreparedStatement) throws SQLException {
        updatePreparedStatement.setDate(1, Date.valueOf(entity.getStartDate()));
        updatePreparedStatement.setDate(2, Date.valueOf(entity.getEndDate()));
        updatePreparedStatement.setLong(3, entity.getId());
    }
}
