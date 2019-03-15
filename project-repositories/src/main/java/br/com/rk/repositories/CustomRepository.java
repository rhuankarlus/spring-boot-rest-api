package br.com.rk.repositories;

import br.com.rk.entities.ProjectEntity;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Rhuan Karlus
 * @since 15/03/19
 */
@NoRepositoryBean
public interface CustomRepository<E extends ProjectEntity> {
}
