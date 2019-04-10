package br.com.rk.controller.audit;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Rhuan Karlus
 * @since 09/04/2019
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuditCrudControllerTest {

    private final WebApplicationContext webApplicationContext;
    private final TestRestTemplate restTemplate;

    @Autowired
    public AuditCrudControllerTest(WebApplicationContext webApplicationContext, TestRestTemplate restTemplate) {
        this.webApplicationContext = webApplicationContext;
        this.restTemplate = restTemplate;
    }

}
