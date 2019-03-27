package br.com.rk.controller.audit;

import br.com.rk.controller.AbstractCrudController;
import br.com.rk.controller.audit.dto.AuditDTO;
import br.com.rk.entities.audit.Audit;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rhuan Karlus
 * @since 27/03/19
 */
@RestController
@RequestMapping("/audit")
public class AuditController extends AbstractCrudController<AuditDTO, Audit> {
}
