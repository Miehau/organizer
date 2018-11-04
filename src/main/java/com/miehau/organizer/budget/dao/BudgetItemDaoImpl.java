package com.miehau.organizer.budget.dao;

import com.miehau.organizer.budget.entity.BudgetItem;
import com.miehau.organizer.budget.exception.ItemAlreadyExists;
import com.miehau.organizer.budget.exception.ItemNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Date;

@Transactional
public class BudgetItemDaoImpl implements BudgetDao {

    @PersistenceContext
    EntityManager em;

    @Override
    public BudgetItem getOne(java.lang.Long id) throws ItemNotFoundException {
        BudgetItem result = em.find(BudgetItem.class, id);
        if (result == null) {
            throw new ItemNotFoundException();
        }
        return result;
    }

    @Override
    public Collection<BudgetItem> getAll() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<BudgetItem> criteriaQuery = builder.createQuery(BudgetItem.class);
        criteriaQuery.from(BudgetItem.class);
        TypedQuery<BudgetItem> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void save(BudgetItem object) throws ItemAlreadyExists {
        if (em.contains(object)) {
            throw new ItemAlreadyExists();
        }
        em.persist(object);
    }

    @Override
    public BudgetItem update(BudgetItem object) throws ItemNotFoundException {
        return em.merge(object);
    }

    @Override
    public void delete(java.lang.Long id) throws ItemNotFoundException {
        try {
            em.remove(this.getOne(id));
        } catch (IllegalArgumentException e) {
            throw new ItemNotFoundException(e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        em.createQuery("delete BudgetItem").executeUpdate();
    }

    @Override
    public Collection<BudgetItem> getItemsBetweenDates(Date startDate, Date endDate) {
        Query query = em.createQuery("select bItem from BudgetItem bItem where bItem.creationDate is not null and bItem.creationDate between :startDate and :endDate");
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }
}
