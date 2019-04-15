package br.com.rk.controller.integration;

import br.com.rk.controller.AbstractControllerTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

/**
 * @author Rhuan Karlus
 * @since 11/04/2019
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public abstract class AbstractControllerIntegrationTest extends AbstractControllerTest {

    

}
