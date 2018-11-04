package com.miehau.organizer.budget.dao;

import com.miehau.organizer.budget.entity.BudgetItem;
import com.miehau.organizer.budget.exception.ItemAlreadyExists;
import com.miehau.organizer.budget.exception.ItemNotFoundException;

import java.util.Collection;
import java.util.Date;

public interface BudgetDao {
    BudgetItem getOne(Long id) throws ItemNotFoundException;
    Collection<BudgetItem> getAll();
    void save(BudgetItem object) throws ItemAlreadyExists;
    BudgetItem update(BudgetItem object) throws ItemNotFoundException;
    void delete(Long id) throws ItemNotFoundException;
    void deleteAll();
    Collection<BudgetItem> getItemsBetweenDates(Date startDate, Date endDate);
}
