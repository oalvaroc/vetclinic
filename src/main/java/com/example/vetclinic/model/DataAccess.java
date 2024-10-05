package com.example.vetclinic.model;

import java.util.List;

public interface DataAccess<T> {

    void create(T entity);

    void update(T entity);

    void delete(T entity);

    List<T> retrieveAll();

    T retrieveById(String id);
}
