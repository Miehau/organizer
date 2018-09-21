package com.miehau.organizer.budget.dao;

import com.miehau.organizer.budget.exception.ItemNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

@Transactional(Transactional.TxType.SUPPORTS)
public abstract class AbstractDao<T, Long> implements Dao<T, java.lang.Long> {
    @PersistenceContext
    EntityManager em;

    private Class<T> domainClass;

    @Override
    public T getOne(java.lang.Long id) throws ItemNotFoundException {
        T result = em.find(getDomainClass(), id);
        if (result == null) {
            throw new ItemNotFoundException();
        }
        return result;
    }

    @Override
    public Collection<T> getAll() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(getDomainClass());
        criteriaQuery.from(getDomainClass());
        TypedQuery<T> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void save(T object) {
        em.persist(object);
    }

    @Override
    public void update(T object) {
        em.merge(object);
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
        em.createQuery("delete " + getDomainClassName()).executeUpdate();
    }

    @SuppressWarnings("unchecked")
    protected Class<T> getDomainClass() {
        if (domainClass == null) {
            ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
            domainClass = (Class<T>) type.getActualTypeArguments()[0];
        }
        return domainClass;
    }

    protected String getDomainClassName() {
        return getDomainClass().getName();
    }

}
