package br.com.rk.controller.integration;

import br.com.rk.controller.AbstractControllerTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

/**
 * @author Rhuan Karlus
 * @since 11/04/2019
 */
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql({"/drop_data.sql", "/data.sql"})
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class AbstractControllerIntegrationTest extends AbstractControllerTest {

}
