package br.com.rk.repositories;

import br.com.rk.entities.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Rhuan Karlus
 * @since 03/03/19
 */
@NoRepositoryBean
public interface ProjectRepository<E extends ProjectEntity> extends JpaRepository<E, Long>, CustomRepository<E>  {
}
