package com.epam.jwd.final_task.dao;

import com.epam.jwd.final_task.exception.DAOException;
import com.epam.jwd.final_task.model.DbEntity;

import java.util.List;
import java.util.Optional;

public interface Dao<T extends DbEntity> {
    T save(T entity) throws DAOException;

    List<T> findAll() throws DAOException;

    Optional<T> findById(Long id) throws DAOException;

    T update(T entity) throws DAOException;

    void delete(Long id) throws DAOException;

}
