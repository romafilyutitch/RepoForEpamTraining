package com.epam.jwd.final_task.dao;

import com.epam.jwd.final_task.exception.DAOException;
import com.epam.jwd.final_task.model.DbEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T extends DbEntity> implements Dao<T> {

    private final String tableName;
    private final String findAllSql = String.format("select %s from %s", ,tableName);
    private final String saveSql = String.format("insert into %s (%s) values (%s) ", tableName);
    private final String updateSql  = String.format("update %s set %s where %s", tableName);
    private final String deleteSql = String.format("delete from %s, where %s", tableName);

    public AbstractDao(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public T save(T entity) throws DAOException {
        return null;
    }

    @Override
    public List<T> findAll() throws DAOException {
        return null;
    }

    @Override
    public Optional<T> findById(Long id) throws DAOException {
        return Optional.empty();
    }

    @Override
    public T update(T entity) throws DAOException {
        return null;
    }

    @Override
    public void delete(Long id) throws DAOException {

    }

    public abstract T getMapperFunction(ResultSet result);

    public abstract void applyStatement(PreparedStatement statement);
}
