package br.com.rk.repositories;

import br.com.rk.entities.ProjectEntity;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Custom repositories abstraction.
 *
 * @author Rhuan Karlus
 * @since 15/03/19
 */
@NoRepositoryBean
public abstract class AbstractCustomRepository<E extends ProjectEntity> implements CustomRepository<E> {

    @PersistenceContext
    protected EntityManager entityManager;

}
