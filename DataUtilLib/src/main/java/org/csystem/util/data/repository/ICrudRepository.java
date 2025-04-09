package org.csystem.util.data.repository;

import java.util.Optional;

/**
 * An extension of {@link IRepository} that provides CRUD operations for managing entities of type {@code T}
 * with identifiers of type {@code Id}. This interface includes methods for deleting, checking existence,
 * and finding entities by their IDs.
 *
 * @param <T>  the type of entity the repository manages
 * @param <Id> the type of the entity's identifier
 */
public interface ICrudRepository<T, Id> extends IRepository<T> {
    /**
     * Deletes all entities with the specified IDs.
     *
     * <p>This method deletes entities from the repository that match the given collection of IDs.</p>
     *
     * @param ids the collection of IDs whose corresponding entities should be deleted
     */
    void deleteAllById(Iterable<? extends Id> ids);

    /**
     * Deletes the entity with the specified ID.
     *
     * <p>This method deletes the entity that matches the given ID from the repository.</p>
     *
     * @param id the ID of the entity to delete
     */
    void deleteById(Id id);

    /**
     * Checks whether an entity with the specified ID exists.
     *
     * @param id the ID to check for existence
     * @return {@code true} if an entity with the given ID exists, {@code false} otherwise
     */
    boolean existsById(Id id);

    /**
     * Finds all entities with the specified IDs.
     *
     * <p>This method retrieves entities from the repository that match the given collection of IDs.</p>
     *
     * @param id the collection of IDs whose corresponding entities should be retrieved
     * @return an {@code Iterable} of entities that match the specified IDs
     */
    Iterable<T> findAllById(Iterable<Id> id);

    /**
     * Finds the entity with the specified ID.
     *
     * <p>This method retrieves an entity that matches the given ID. If no entity is found,
     * it returns an empty {@code Optional}.</p>
     *
     * @param id the ID of the entity to retrieve
     * @return an {@code Optional} containing the matching entity, or an empty {@code Optional} if no entity is found
     */
    Optional<T> findById(Id id);
}
