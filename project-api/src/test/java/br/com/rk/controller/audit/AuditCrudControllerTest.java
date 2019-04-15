package br.com.rk.controller.audit;

import org.junit.Test;
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

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void test() {
    }

}
