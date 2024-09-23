package org.csystem.util.data.repository;

/**
 * Abstraction of general CRUD operations with id
 * A functional interface that represents a repository of results.
 * This interface has five abstract methods
 * {@code deleteAllById(Iterable<? extends Id> ids),
 *      deleteById(Id id),
 *      deleteById(Id id)
 *      existsById(Id id)
 *      findAllById(Iterable<Id> id)
 *      findById(Id id)
 * }
 * that repository a result of types
 * {@code void,
 *      boolean,
 *      Iterable<T>,
 *      Optional<T>
 * }.
 * @param <T, Id> the type of result repository by this repository
 * @author Oğuz Karan
 * @version 3.3.0
 */

import java.util.Optional;

public interface ICrudRepository<T, Id> extends IRepository<T> {
    void deleteAllById(Iterable<? extends Id> ids);
    void deleteById(Id id);
    boolean existsById(Id id);
    Iterable<T> findAllById(Iterable<Id> id);
    Optional<T> findById(Id id);
}
