package com.miehau.organizer.budget.service;

import com.miehau.organizer.budget.dao.BudgetDao;
import com.miehau.organizer.budget.entity.BudgetItem;
import com.miehau.organizer.budget.exception.ItemAlreadyExists;
import com.miehau.organizer.budget.exception.ItemNotFoundException;

import java.util.Collection;
import java.util.Date;

public class BudgetServiceImpl implements BudgetService {
    private BudgetDao budgetDao;

    public BudgetServiceImpl(BudgetDao budgetDao) {
        this.budgetDao = budgetDao;
    }

    @Override
    public Collection<BudgetItem> getAllItems() {
        return budgetDao.getAll();
    }

    @Override
    public Collection<BudgetItem> getBudgetItemsBetweenDates(Date startDate, Date endDate) {
        return budgetDao.getItemsBetweenDates(startDate, endDate);
    }

    @Override
    public void addItem(BudgetItem itemToAdd) throws ItemAlreadyExists {
        budgetDao.save(itemToAdd);
    }

    @Override
    public void deleteItem(Long id) throws ItemNotFoundException {
        budgetDao.delete(id);
    }

    @Override
    public void update(BudgetItem itemToUpdate) throws ItemNotFoundException {
        budgetDao.update(itemToUpdate);
    }
}
