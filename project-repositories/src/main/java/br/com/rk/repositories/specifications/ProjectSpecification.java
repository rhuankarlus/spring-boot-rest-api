package br.com.rk.repositories.specifications;

import br.com.rk.entities.ProjectEntity;
import org.springframework.data.jpa.domain.Specification;

/**
 * Project specifications should implement this interface
 *
 * @author Rhuan Karlus
 * @since 26/03/19
 */
public interface ProjectSpecification<E extends ProjectEntity> extends Specification<E> {

}
