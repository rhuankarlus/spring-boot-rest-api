package br.com.rk.repositories;

import br.com.rk.entities.ProjectEntity;
import br.com.rk.repositories.exception.PersistenceMethodNotAllowedException;

import java.util.List;

/**
 * This repository is usefull when you have a read only bean. In this case your repository can implement this interface
 * in order to prevent naughty developers to update/delete those properties.
 *
 * @author Rhuan Karlus
 * @since 5/23/19
 */
public interface ReadOnlyRepository<E extends ProjectEntity> extends ProjectRepository<E> {

    /**
     * <b>WARNING:</b> You can't persist this bean, this method only throw an exception
     *
     * @param iterable Iterable of beans
     * @param <S>      The bean type definition
     * @return nothing
     * @throws PersistenceMethodNotAllowedException always
     */
    @Override
    default <S extends E> List<S> saveAll(Iterable<S> iterable) throws PersistenceMethodNotAllowedException {
        throw new PersistenceMethodNotAllowedException("You can't persist this bean. It is a read only bean.");
    }

    /**
     * <b>WARNING:</b> You can't persist this bean, this method only throw an exception
     *
     * @param s   Bean to be persisted
     * @param <S> The bean type definition
     * @return nothing
     * @throws PersistenceMethodNotAllowedException always
     */
    @Override
    default <S extends E> S saveAndFlush(S s) throws PersistenceMethodNotAllowedException {
        throw new PersistenceMethodNotAllowedException("You can't persist this bean. It is a read only bean.");
    }

    /**
     * <b>WARNING:</b> You can't delete this bean, this method only throw an exception
     *
     * @param iterable The iterable of beans to be deleted
     * @throws PersistenceMethodNotAllowedException always
     */
    @Override
    default void deleteInBatch(Iterable<E> iterable) throws PersistenceMethodNotAllowedException {
        throw new PersistenceMethodNotAllowedException("You can't delete this bean. It is immutable, please check the Github repository for more information.");
    }

    /**
     * <b>WARNING:</b> You can't delete this bean, this method only throw an exception
     *
     * @throws PersistenceMethodNotAllowedException always
     */
    @Override
    default void deleteAllInBatch() throws PersistenceMethodNotAllowedException {
        throw new PersistenceMethodNotAllowedException("You can't delete this bean. It is immutable, please check the Github repository for more information.");
    }

    /**
     * <b>WARNING:</b> You can't persist this bean, this method only throw an exception
     *
     * @param s   Bean to be persisted
     * @param <S> The bean type definition
     * @return nothing
     * @throws PersistenceMethodNotAllowedException always
     */
    @Override
    default <S extends E> S save(S s) throws PersistenceMethodNotAllowedException {
        throw new PersistenceMethodNotAllowedException("You can't persist this bean. It is a read only bean.");
    }

    /**
     * <b>WARNING:</b> You can't delete this bean, this method only throw an exception
     *
     * @param id The id of the bean to be deleted
     * @throws PersistenceMethodNotAllowedException always
     */
    @Override
    default void deleteById(Long id) throws PersistenceMethodNotAllowedException {
        throw new PersistenceMethodNotAllowedException("You can't delete this bean. It is immutable, please check the Github repository for more information.");
    }

    /**
     * <b>WARNING:</b> You can't delete this bean, this method only throw an exception
     *
     * @param projectEntity The entity to be deleted
     * @throws PersistenceMethodNotAllowedException always
     */
    @Override
    default void delete(E projectEntity) throws PersistenceMethodNotAllowedException {
        throw new PersistenceMethodNotAllowedException("You can't delete this bean. It is immutable, please check the Github repository for more information.");
    }

    /**
     * <b>WARNING:</b> You can't delete this bean, this method only throw an exception
     *
     * @param iterable The iterable of beans to be deleted
     * @throws PersistenceMethodNotAllowedException always
     */
    @Override
    default void deleteAll(Iterable<? extends E> iterable) throws PersistenceMethodNotAllowedException {
        throw new PersistenceMethodNotAllowedException("You can't delete this bean. It is immutable, please check the Github repository for more information.");
    }

    /**
     * <b>WARNING:</b> You can't delete this bean, this method only throw an exception
     *
     * @throws PersistenceMethodNotAllowedException always
     */
    @Override
    default void deleteAll() throws PersistenceMethodNotAllowedException {
        throw new PersistenceMethodNotAllowedException("You can't delete this bean. It is immutable, please check the Github repository for more information.");
    }

}
