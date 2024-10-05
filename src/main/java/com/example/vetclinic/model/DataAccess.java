package com.example.vetclinic.model;

public interface DataAccess<T> {
    void register(T entity);
    void update(T entity);
    void delete(T entity);
    void retrieve(T entity);
}
