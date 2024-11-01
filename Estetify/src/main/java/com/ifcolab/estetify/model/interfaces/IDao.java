package com.ifcolab.estetify.model.interfaces;

import java.util.List;

public interface IDao<T> {
    
    void save(T obj);
    
    T find(int id);
    
    List<T> findAll();
    
    void update(T obj);
    
    boolean delete(int id);
}
