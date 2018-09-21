package com.miehau.organizer.budget.dao;

import com.miehau.organizer.budget.entity.BudgetItem;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Date;

@Transactional
public class BudgetItemDaoImpl extends AbstractDao<BudgetItem, Long> implements BudgetDao {

    @Override
    public Collection<BudgetItem> getItemsBetweenDates(Date startDate, Date endDate) {
        Query query = em.createQuery("select bItem from BudgetItem bItem where bItem.creationDate is not null and bItem.creationDate between :startDate and :endDate");
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }
}
