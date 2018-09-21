package com.miehau.organizer.budget.dao;

import com.miehau.organizer.budget.exception.ItemNotFoundException;

import java.io.Serializable;
import java.util.Collection;

public interface Dao<T,K extends Serializable> {
    T getOne(K id) throws ItemNotFoundException;
    Collection<T> getAll();
    void save(T object);
    void update(T object);
    void delete(K id) throws ItemNotFoundException;
    void deleteAll();
}
