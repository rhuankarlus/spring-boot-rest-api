package br.com.rk.controller;

import br.com.rk.controller.dto.DTO;
import br.com.rk.converters.Conversor;
import br.com.rk.entities.Entidade;

/**
 * Interface to ensure object has a conversor from Entity to DTO and vice versa
 *
 * @author Rhuan Karlus
 * @since 14/03/19
 */
public interface HasConversor<D extends DTO, E extends Entidade> {

    /**
     * @return The DTO's conversor to the Entity
     */
    Conversor<D, E> getConversor();

}
