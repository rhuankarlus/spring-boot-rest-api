package br.com.rk.repositories;

import br.com.rk.entities.Entidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Rhuan Karlus
 * @since 03/03/19
 */
@NoRepositoryBean
public interface Repositorio<E extends Entidade> extends JpaRepository<E, Long> {
}
