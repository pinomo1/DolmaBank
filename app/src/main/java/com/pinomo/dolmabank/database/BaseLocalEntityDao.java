package com.pinomo.dolmabank.database;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface for the local database operations
 *
 * @param <T> the entity type
 */
public interface BaseLocalEntityDao<T extends LocalPayloadEntity>{

    /**
     * Get all the entities
     *
     * @return A list of entities
     */
    List<T> getAll();

    /**
     * Get an entity by its id
     *
     * @param uid The id of the entity
     * @return The entity
     */
    T getById(Long uid);

    /**
     * Insert an entity
     *
     * @param entity The entity to insert
     * @return The id of the inserted entity
     */
    Long insert(T entity);

    /**
     * Delete an entity
     *
     * @param entity The entity to delete
     */
    void delete(T entity);

    /**
     * Update an entity
     *
     * @param entity The entity to update
     */
    void update(T entity);
}
