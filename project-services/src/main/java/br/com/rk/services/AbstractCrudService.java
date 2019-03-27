package br.com.rk.services;

import br.com.rk.entities.ProjectEntity;
import br.com.rk.repositories.ProjectRepository;
import br.com.rk.services.exception.ServicoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service abstraction for persistent models
 *
 * @author Rhuan Karlus
 * @since 04/03/2019
 */
public abstract class AbstractCrudCrudService<E extends ProjectEntity> implements ProjectCrudService<E> {

    @Autowired
    private ProjectRepository<E> projectRepository;

    protected ProjectRepository<E> getProjectRepository() {
        return projectRepository;
    }

    @Override
    public E findById(Long id) throws ServicoException {
        if (id == null) {
            throw new ServicoException("O ID informado não pode ser nulo.");
        }

        return projectRepository.findById(id).orElseThrow(() -> new ServicoException("ProjectEntity com ID " + id + " não encontrada."));
    }

    @Override
    public E persist(E entidade) throws ServicoException {
        try {
            return projectRepository.save(entidade);
        } catch (Exception e) {
            throw new ServicoException("Erro ao tentar persistir a entidade", e);
        }
    }

    @Override
    public void delete(Long id) throws ServicoException {
        try {
            projectRepository.deleteById(id);
        } catch (Exception e) {
            throw new ServicoException("Erro ao tentar deletar a entidade", e);
        }
    }

    @Override
    public List<E> findAll() throws ServicoException {
        try {
            return projectRepository.findAll();
        } catch (Exception e) {
            throw new ServicoException("Erro ao tentar ler a lista de entidades do banco", e);
        }
    }

    @Override
    public Page<E> findAll(Pageable paginador) throws ServicoException {
        try {
            return projectRepository.findAll(paginador);
        } catch (Exception e) {
            throw new ServicoException("Erro ao tentar ler a página de entidades do banco", e);
        }
    }

}
