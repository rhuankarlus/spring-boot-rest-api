package br.com.rk.services;

import br.com.rk.entities.ProjectEntity;
import br.com.rk.repositories.ProjectRepository;
import br.com.rk.services.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * Service abstraction for persistent models
 *
 * @author Rhuan Karlus
 * @since 04/03/2019
 */
public abstract class AbstractCrudService<E extends ProjectEntity> implements ProjectCrudService<E> {

    @Autowired
    private ProjectRepository<E> projectRepository;

    protected ProjectRepository<E> getProjectRepository() {
        return projectRepository;
    }

    @Override
    public E findById(long id) throws ServiceException {
        return projectRepository.findById(id).orElseThrow(() -> new ServiceException("Entity with ID " + id + " not found."));
    }

    @Override
    public E persist(E entidade) throws ServiceException {
        if (entidade == null) {
            throw new ServiceException("The entity shouldn't be null.");
        }

        try {
            return projectRepository.save(entidade);
        } catch (Exception e) {
            throw new ServiceException("Error trying to persist entity", e);
        }
    }

    @Override
    public void delete(long id) throws ServiceException {
        try {
            projectRepository.deleteById(id);
        } catch (Exception e) {
            throw new ServiceException("Error trying to delete the entity", e);
        }
    }

    @Override
    public List<E> findAll() throws ServiceException {
        try {
            return projectRepository.findAll();
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
            return projectRepository.findAll(pageable);
        } catch (Exception e) {
            throw new ServiceException("Error when trying to read the entities page from database", e);
        }
    }

    @Override
    public Page<E> findByExample(final E entity, final Pageable pageable) throws ServiceException {
        validateParams(entity, pageable);
        return getProjectRepository().findAll(buildSpecifications(entity), pageable);
    }

    protected abstract void validateParams(final E entity, final Pageable pageable) throws ServiceException;

    protected abstract Specification<E> buildSpecifications(final E entity);

}
