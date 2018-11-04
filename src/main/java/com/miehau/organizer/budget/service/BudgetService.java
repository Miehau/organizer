package com.miehau.organizer.budget.service;

import com.miehau.organizer.budget.entity.BudgetItem;
import com.miehau.organizer.budget.exception.ItemAlreadyExists;
import com.miehau.organizer.budget.exception.ItemNotFoundException;

import java.util.Collection;
import java.util.Date;

public interface BudgetService {
     Collection<BudgetItem> getAllItems();
     Collection<BudgetItem> getBudgetItemsBetweenDates(Date startDate, Date endDate);
     void addItem(BudgetItem itemToAdd) throws ItemAlreadyExists;
     void deleteItem(java.lang.Long id) throws ItemNotFoundException;
     void update(BudgetItem itemToUpdate) throws ItemNotFoundException;

}
