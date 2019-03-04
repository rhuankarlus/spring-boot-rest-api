package br.com.rk.services;

import br.com.rk.entities.Entidade;
import br.com.rk.repositories.Repositorio;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Rhuan Karlus
 * @since 04/03/2019
 */
public abstract class AbstractServico<E extends Entidade> implements Servico<E> {

    @Autowired
    private Repositorio<E> repositorio;

    protected Repositorio<E> getRepositorio() {
        return repositorio;
    }

    @Override
    public E buscar(Long id) {
        return null;
    }

    @Override
    public E salvar(E entidade) {
        return null;
    }

    @Override
    public void deletar(Long id) {

    }

}
