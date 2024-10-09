package org.csystem.util.data.repository;

/**
 * A generic repository interface providing basic CRUD operations for entities of type {@code T}.
 * This interface defines methods for counting, saving, deleting, and finding entities.
 *
 * @param <T> the type of entity managed by this repository
 */
public interface IRepository<T> {

    /**
     * Returns the total number of entities in the repository.
     *
     * @return the total number of entities
     */
    long count();

    /**
     * Deletes the given entity from the repository.
     *
     * @param entity the entity to delete
     */
    void delete(T entity);

    /**
     * Deletes all entities in the repository.
     */
    void deleteAll();

    /**
     * Deletes the given entities from the repository.
     *
     * @param entities the entities to delete
     */
    void deleteAll(Iterable<? extends T> entities);

    /**
     * Returns all entities from the repository.
     *
     * @return an {@code Iterable} of all entities in the repository
     */
    Iterable<T> findAll();

    /**
     * Saves the given entity to the repository.
     *
     * <p>If the entity already exists, it may be updated depending on the repository implementation.</p>
     *
     * @param <S> the type of the entity to be saved, which must extend {@code T}
     * @param entity the entity to save
     * @return the saved entity
     */
    <S extends T> S save(S entity);

    /**
     * Saves all given entities to the repository.
     *
     * <p>If any entities already exist, they may be updated depending on the repository implementation.</p>
     *
     * @param <S> the type of the entities to be saved, which must extend {@code T}
     * @param entities the entities to save
     * @return an {@code Iterable} of the saved entities
     */
    <S extends T> Iterable<S> saveAll(Iterable<S> entities);
}
