package br.com.rk.converters;

import br.com.rk.controller.dto.DTO;
import br.com.rk.entities.ProjectEntity;
import br.com.rk.exceptions.EnumNotFoundException;

/**
 * @author Rhuan Karlus
 * @since 04/03/2019
 */
public interface Conversor<D extends DTO, E extends ProjectEntity> {

    E toEntity(D dto) throws ConverterException;

    D toDTO(E entidade) throws ConverterException;

}
