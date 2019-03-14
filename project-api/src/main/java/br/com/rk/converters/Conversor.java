package br.com.rk.converters;

import br.com.rk.controller.dto.DTO;
import br.com.rk.entities.Entidade;

/**
 * @author Rhuan Karlus
 * @since 04/03/2019
 */
public interface Conversor<D extends DTO, E extends Entidade> {

    E toEntity(D dto);

    D toDTO(E entidade);

}
