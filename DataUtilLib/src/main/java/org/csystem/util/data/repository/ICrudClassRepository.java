package org.csystem.util.data.repository;

/**
 * Abstraction of general CRUD operations without id
 * This interface has three abstract methods
 * {@code exists(T entity),
 *      findBy(T entity),
 *      findFirst(T entity)
 * }
 * that repository a result of types
 * {@code boolean,
 *      Iterable<T>,
 *      Optional<T>
 * }.
 * @param <T> the type of result repository by this repository
 * @author Oğuz Karan
 * @version 3.3.0
 */

import java.util.Optional;
import java.util.stream.StreamSupport;

public interface ICrudClassRepository<T> extends IRepository<T> {
    boolean exists(T entity);
    Iterable<T> findBy(T entity);
    default Optional<T> findFirst(T entity)
    {
        return StreamSupport.stream(findBy(entity).spliterator(), false).findFirst();
    }
}
