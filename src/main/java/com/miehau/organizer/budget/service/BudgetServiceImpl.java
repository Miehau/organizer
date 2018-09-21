package com.miehau.organizer.budget.service;

import com.miehau.organizer.budget.dao.BudgetDao;
import com.miehau.organizer.budget.dao.BudgetItemDaoImpl;
import com.miehau.organizer.budget.entity.BudgetItem;

import java.util.Collection;
import java.util.Date;

public class BudgetServiceImpl implements BudgetService {
    private BudgetDao budgetDao;

    public BudgetServiceImpl(BudgetDao budgetDao) {
        this.budgetDao = budgetDao;
    }

    @Override
    public Collection<BudgetItem> getAllBudgetItems() {
        return budgetDao.getAll();
    }

    @Override
    public Collection<BudgetItem> getBudgetItemsBetweenDates(Date startDate, Date endDate) {
        return budgetDao.getItemsBetweenDates(startDate, endDate);
    }

    @Override
    public void addItem(BudgetItem itemToAdd) {

    }

    @Override
    public void deleteItem(Long id) {

    }

    @Override
    public void update(BudgetItem itemToUpdate) {

    }
}
