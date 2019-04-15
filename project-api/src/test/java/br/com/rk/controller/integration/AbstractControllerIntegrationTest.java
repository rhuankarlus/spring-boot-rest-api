package br.com.rk.controller.integration;

import br.com.rk.controller.AbstractControllerTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.jdbc.Sql;

/**
 * @author Rhuan Karlus
 * @since 11/04/2019
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql({ "/drop_data.sql", "/data.sql" })
@AutoConfigureMockMvc
public abstract class AbstractControllerIntegrationTest extends AbstractControllerTest {

}
