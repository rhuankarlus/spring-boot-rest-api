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
    public E findById(Long id) throws ServiceException {
        if (id == null) {
            throw new ServiceException("The ID can't be null.");
        }

        return projectRepository.findById(id).orElseThrow(() -> new ServiceException("Entity with ID " + id + " not found."));
    }

    @Override
    public E persist(E entidade) throws ServiceException {
        try {
            return projectRepository.save(entidade);
        } catch (Exception e) {
            throw new ServiceException("Erro ao tentar persistir a entidade", e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            projectRepository.deleteById(id);
        } catch (Exception e) {
            throw new ServiceException("Erro ao tentar deletar a entidade", e);
        }
    }

    @Override
    public List<E> findAll() throws ServiceException {
        try {
            return projectRepository.findAll();
        } catch (Exception e) {
            throw new ServiceException("Erro ao tentar ler a lista de entidades do banco", e);
        }
    }

    @Override
    public Page<E> findAll(Pageable paginador) throws ServiceException {
        try {
            return projectRepository.findAll(paginador);
        } catch (Exception e) {
            throw new ServiceException("Erro ao tentar ler a página de entidades do banco", e);
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
