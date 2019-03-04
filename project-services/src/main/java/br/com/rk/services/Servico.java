package br.com.rk.services;

import br.com.rk.entities.Entidade;
import br.com.rk.services.exception.ServicoException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Rhuan Karlus
 * @since 04/03/19
 */
public interface Servico<E extends Entidade> {

    /**
     * Busca uma entidade pelo seu ID
     *
     * @param id ID da entidade
     * @return retorna a entidade ou lança uma exceção caso nenhuma entidade com esse ID tenha sido encontrada.
     * @throws ServicoException caso nenhuma entidade com o ID informado tenha sido encontrada ou haja algum erro
     *                          durante a busca pela entidade
     */
    E buscar(Long id) throws ServicoException;

    /**
     * Persiste os dados de uma entidade no banco
     *
     * @param entidade Entidade que será persistida
     * @return A entidade atualizada
     * @throws ServicoException Caso haja algum erro durante a persistência da entidade
     */
    E salvar(E entidade) throws ServicoException;

    /**
     * Remove uma entidade do banco
     *
     * @param id ID da entidade que será removida
     * @throws ServicoException Caso ocorra algum erro durante a remoção da entidade
     */
    void deletar(Long id) throws ServicoException;

    /**
     * @return Todas as entidades desse tipo. <b>CUIDADO AO USAR ESSE MÉTODO, PODE DEGRADAR MUITO A PERFORMANCE!</b>
     * @throws ServicoException Caso ocorra algum erro durante a busca
     */
    List<E> findAll() throws ServicoException;

    /**
     * Encontra todas as entidades paginando de acordo com o objeto
     *
     * @param paginador Objeto de paginação do framework
     * @return A página de entidades encontrada
     * @throws ServicoException Caso ocorra algum erro durante a busca
     */
    Page<E> findAll(Pageable paginador) throws ServicoException;

}
