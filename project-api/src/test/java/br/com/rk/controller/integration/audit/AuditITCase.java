package br.com.rk.controller.integration.audit;

import br.com.rk.controller.integration.AbstractControllerIntegrationTest;
import org.junit.Test;
import org.springframework.http.HttpStatus;

/**
 * @author Rhuan Karlus
 * @since 15/04/2019
 */
public class AuditITCase extends AbstractControllerIntegrationTest {

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        doGetExpectStatus("/audit", HttpStatus.OK);
    }

}
