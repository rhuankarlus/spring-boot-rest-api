package br.com.rk.services;

import br.com.rk.entities.ProjectEntity;
import br.com.rk.services.exception.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Rhuan Karlus
 * @since 04/03/19
 */
public interface ProjectCrudService<E extends ProjectEntity> {

    /**
     * Busca uma entidade pelo seu ID
     *
     * @param id ID da entidade
     * @return retorna a entidade ou lança uma exceção caso nenhuma entidade com esse ID tenha sido encontrada.
     * @throws ServiceException caso nenhuma entidade com o ID informado tenha sido encontrada ou haja algum erro
     *                          durante a busca pela entidade
     */
    E findById(long id) throws ServiceException;

    /**
     * Persiste os dados de uma entidade no banco
     *
     * @param entidade ProjectEntity que será persistida
     * @return A entidade atualizada
     * @throws ServiceException Caso haja algum erro durante a persistência da entidade
     */
    E persist(E entidade) throws ServiceException;

    /**
     * Remove uma entidade do banco
     *
     * @param id ID da entidade que será removida
     * @throws ServiceException Caso ocorra algum erro durante a remoção da entidade
     */
    void delete(long id) throws ServiceException;

    /**
     * @return Todas as entidades desse tipo. <b>CUIDADO AO USAR ESSE MÉTODO, PODE DEGRADAR MUITO A PERFORMANCE!</b>
     * @throws ServiceException Caso ocorra algum erro durante a busca
     */
    List<E> findAll() throws ServiceException;

    /**
     * Encontra todas as entidades paginando de acordo com o objeto
     *
     * @param paginador Objeto de paginação do framework
     * @return A página de entidades encontrada
     * @throws ServiceException Caso ocorra algum erro durante a busca
     */
    Page<E> findAll(Pageable paginador) throws ServiceException;

    /**
     * Search for every {@code entity} by the example
     *
     * @param entity   Example object to be search
     * @param pageable Pagination object to paginate results
     * @return A page with the paginated result based on the example
     */
    Page<E> findByExample(final E entity, final Pageable pageable) throws ServiceException;

}
