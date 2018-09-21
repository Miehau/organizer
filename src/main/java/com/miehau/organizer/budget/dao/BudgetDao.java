package com.miehau.organizer.budget.dao;

import com.miehau.organizer.budget.entity.BudgetItem;

import java.util.Collection;
import java.util.Date;

public interface BudgetDao extends Dao<BudgetItem, java.lang.Long> {
    Collection<BudgetItem> getItemsBetweenDates(Date startDate, Date endDate);
}
