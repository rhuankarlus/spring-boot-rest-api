package br.com.rk.services;

import br.com.rk.entities.Entidade;

/**
 * @author Rhuan Karlus
 * @since 04/03/19
 */
public interface Servico<E extends Entidade> {

    E buscar(Long id);

    E salvar(E entidade);

    void deletar(Long id);

}
