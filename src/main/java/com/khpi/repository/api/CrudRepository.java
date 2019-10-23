package com.khpi.repository.api;

public interface CrudRepository<T, P> {

    T save(T t);

    T find(P p);

    T update(P p, T t);

    void delete(P p);
}
