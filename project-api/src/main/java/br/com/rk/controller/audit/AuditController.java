package br.com.rk.controller.audit;

import br.com.rk.controller.AbstractCrudController;
import br.com.rk.controller.audit.dto.AuditDTO;
import br.com.rk.controller.dto.ProjectResponse;
import br.com.rk.entities.audit.Audit;
import br.com.rk.services.exception.ServiceException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rhuan Karlus
 * @since 27/03/19
 */
@RestController
@RequestMapping("/audit")
public class AuditController extends AbstractCrudController<AuditDTO, Audit> {

//    public ResponseEntity<ProjectResponse> findAll(@PageableDefault final Pageable pageable) throws ServiceException {
//        return super.findAll(pageable);
//    }

}
