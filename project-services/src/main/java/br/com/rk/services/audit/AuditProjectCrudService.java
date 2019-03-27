package br.com.rk.services.audit;

import br.com.rk.entities.audit.Audit;
import br.com.rk.services.ProjectCrudService;
import br.com.rk.services.exception.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service that handle operations over {@link Audit} entity
 *
 * @author Rhuan Karlus
 * @since 26/03/19
 */
public interface AuditProjectCrudService extends ProjectCrudService<Audit> {

    /**
     * Search for every {@link Audit} by the example
     *
     * @param audit    Example object to be search
     * @param pageable Pagination object to paginate results
     * @return A page with the paginated result based on the example
     */
    Page<Audit> findByExample(final Audit audit, final Pageable pageable) throws ServiceException;

}
