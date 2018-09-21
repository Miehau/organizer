package com.miehau.organizer.budget.service;

import com.miehau.organizer.budget.entity.BudgetItem;

import java.util.Collection;
import java.util.Date;

public interface BudgetService {
     Collection<BudgetItem> getAllBudgetItems();
     Collection<BudgetItem> getBudgetItemsBetweenDates(Date startDate, Date endDate);
     void addItem(BudgetItem itemToAdd);
     void deleteItem(java.lang.Long id);
     void update(BudgetItem itemToUpdate);

}
