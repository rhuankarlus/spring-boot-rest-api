package br.com.rk.controller;

import br.com.rk.entities.ProjectEntity;
import br.com.rk.services.Servico;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Rhuan Karlus
 * @since 04/03/2019
 */
public abstract class AbstractControle<E extends ProjectEntity> {

    @Autowired
    private Servico<E> servico;

    protected Servico<E> getServico() {
        return this.servico;
    }

}
