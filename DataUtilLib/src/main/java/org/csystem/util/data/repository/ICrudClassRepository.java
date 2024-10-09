package org.csystem.util.data.repository;

import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * An extension of {@link IRepository} that provides additional CRUD-related operations
 * for managing entities of type {@code T}. This interface includes methods to check
 * the existence of entities, find matching entities, and retrieve the first matching entity.
 *
 * @param <T> the type of entity that the repository manages
 */
public interface ICrudClassRepository<T> extends IRepository<T> {
    /**
     * Checks whether a given entity exists in the repository.
     *
     * @param entity the entity to check for existence
     * @return {@code true} if the entity exists, {@code false} otherwise
     */
    boolean exists(T entity);

    /**
     * Finds all entities in the repository that match the provided entity.
     *
     * <p>This method returns an {@code Iterable} containing all entities that are considered to match the
     * given entity based on the implementation's matching criteria.</p>
     *
     * @param entity the entity used as a filter for the search
     * @return an {@code Iterable} of entities that match the provided entity
     */
    Iterable<T> findBy(T entity);

    /**
     * Finds the first entity that matches the provided entity from the result set.
     *
     * <p>This is a default method that streams the result of {@link #findBy(Object)} and returns the first match,
     * if available. If no matching entity is found, an empty {@code Optional} is returned.</p>
     *
     * @param entity the entity used as a filter for the search
     * @return an {@code Optional} containing the first matching entity, or an empty {@code Optional} if no match is found
     */
    default Optional<T> findFirst(T entity)
    {
        return StreamSupport.stream(findBy(entity).spliterator(), false).findFirst();
    }
}
