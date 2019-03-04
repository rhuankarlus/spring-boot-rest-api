package br.com.rk.services;

import br.com.rk.entities.Entidade;
import br.com.rk.services.exception.ServicoException;

/**
 * @author Rhuan Karlus
 * @since 04/03/19
 */
public interface Servico<E extends Entidade> {

    E buscar(Long id) throws ServicoException;

    E salvar(E entidade) throws ServicoException;

    void deletar(Long id) throws ServicoException;

}
