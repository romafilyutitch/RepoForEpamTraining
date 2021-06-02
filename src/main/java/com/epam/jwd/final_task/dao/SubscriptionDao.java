package com.epam.jwd.final_task.dao;

import com.epam.jwd.final_task.exception.DAOException;
import com.epam.jwd.final_task.model.Subscription;

import java.time.LocalDate;
import java.util.List;

public interface SubscriptionDao extends Dao<Subscription> {
    List<Subscription> findByStartDate(LocalDate startDate) throws DAOException;

    List<Subscription> findByEndDate(LocalDate endDate) throws DAOException;

    List<Subscription> findBetween(LocalDate startDate, LocalDate endDate) throws DAOException;

}
