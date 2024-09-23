package org.csystem.util.data.repository;

/**
 * Abstraction of general CRUD operations without id
 * A functional interface that represents a repository of results.
 * This interface has seven abstract methods
 * {@code count(),
 *      delete(T entity),
 *      deleteAll(),
 *      deleteAll(Iterable<? extends T> entities),
 *      findAll(),
 *      save(S entity),
 *      saveAll(Iterable<S> entities)
 * }
 * that repository a result of types
 * {@code long,
 *      void,
 *      Iterable<T>,
 *      <S extends T> S,
 *      <S extends T> Iterable<S>
 * }.
 * @param <T> the type of result repository by this repository
 * @author Oğuz Karan
 * @version 3.3.0
 */

public interface IRepository<T> {
    long count();
    void delete(T entity);
    void deleteAll();
    void deleteAll(Iterable<? extends T> entities);
    Iterable<T> findAll();
    <S extends T> S save(S entity);
    <S extends T> Iterable<S> saveAll(Iterable<S> entities);
}
