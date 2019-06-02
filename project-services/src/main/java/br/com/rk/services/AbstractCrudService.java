package br.com.rk.services;

import br.com.rk.entities.ProjectEntity;
import br.com.rk.repositories.ProjectRepository;
import br.com.rk.services.exception.EntityNotFoundException;
import br.com.rk.services.exception.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Service abstraction for persistent models
 *
 * @author Rhuan Karlus
 * @since 04/03/2019
 */
public abstract class AbstractCrudService<E extends ProjectEntity> implements ProjectCrudService<E> {

    /**
     * Gets the project repository for this specific service
     *
     * @param <R> The typed repository to retrieve
     * @return The injected repository
     */
    protected abstract ProjectRepository<E> getRepository();

    /**
     * Build all specifications in order to find this entity.
     *
     * @param entity The entity that will be search
     * @return The specifications chain
     */
    protected abstract Specification<E> buildAllSpecifications(final E entity);

    @Override
    public E findById(long id) throws ServiceException {
        return getRepository().findById(id).orElseThrow(() -> new EntityNotFoundException("Entity with ID " + id + " not found."));
    }

    @Override
    public E persist(E entidade) throws ServiceException {
        if (entidade == null) {
            throw new ServiceException("The entity shouldn't be null.");
        }

        try {
            return getRepository().save(entidade);
        } catch (Exception e) {
            throw new ServiceException("Error trying to persist entity", e);
        }
    }

    @Override
    public void delete(long id) throws ServiceException {
        try {
            getRepository().deleteById(id);
        } catch (Exception e) {
            throw new ServiceException("Error trying to delete the entity", e);
        }
    }

    @Override
    public List<E> findAll() throws ServiceException {
        try {
            return getRepository().findAll();
        } catch (Exception e) {
            throw new ServiceException("Error when trying to read the entities list from database", e);
        }
    }

    @Override
    public Page<E> findAll(final Pageable pageable) throws ServiceException {
        if (pageable == null) {
            throw new ServiceException("Can't find page with a null paginator.");
        }

        try {
            return getRepository().findAll(pageable);
        } catch (Exception e) {
            throw new ServiceException("Error when trying to read the entities page from database", e);
        }
    }

    @Override
    public Page<E> findByExample(final E entity, final Pageable pageable) throws ServiceException {
        validateBeforeFindExample(entity, pageable);
        final Page<E> pageFiltered = getRepository().findAll(buildAllSpecifications(entity), pageable);
        if (pageFiltered == null || pageFiltered.getContent() == null || pageFiltered.getContent().size() == 0) {
            throw new EntityNotFoundException("No entity found");
        }

        return pageFiltered;
    }

    /**
     * Validates all parameters from entity and pageable objects before send them to the findByExample repository method.
     *
     * @param entity   Entity that will be validated
     * @param pageable Pageable object to slice results from database
     * @throws ServiceException If any error occurs or if some incongruence was found
     */
    protected void validateBeforeFindExample(final E entity, final Pageable pageable) throws ServiceException {
        try {
            Assert.notNull(entity, "The entity object can't be null.");
            Assert.notNull(pageable, "The pageable object can't be null.");
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}
