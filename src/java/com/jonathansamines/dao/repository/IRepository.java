package com.jonathansamines.dao.repository;

import java.util.ArrayList;

/**
 * Map a list of generic operations
 * @author jonathansamines
 * @param <T> Model or entity that will be processed.
 */
public interface IRepository <T> {
    public ArrayList<T> get();
    public T getById(int id);
    public boolean create(T o);
    public boolean update(T o);
}
