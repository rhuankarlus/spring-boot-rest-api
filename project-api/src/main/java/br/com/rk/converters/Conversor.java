package br.com.rk.converters;

import br.com.rk.controller.dto.DTO;
import br.com.rk.entities.ProjectEntity;

/**
 * @author Rhuan Karlus
 * @since 04/03/2019
 */
public interface Conversor<D extends DTO, E extends ProjectEntity> {

    E toEntity(D dto);

    D toDTO(E entidade);

}
