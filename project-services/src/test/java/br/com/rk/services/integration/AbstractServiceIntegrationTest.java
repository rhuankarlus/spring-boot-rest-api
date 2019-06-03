package br.com.rk.services.integration;

import br.com.rk.IntegrationTestContextConfiguration;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author Rhuan Karlus
 * @since 6/3/19
 */
@ExtendWith(SpringExtension.class)
@Sql({"/drop_data.sql", "/data.sql"})
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        properties = {
                "spring.datasource.name=HikariCP-Pool",
                "spring.datasource.type=com.zaxxer.hikari.HikariDataSource",
                "spring.datasource.driver-class-name=org.hsqldb.jdbcDriver",
                "spring.datasource.url=jdbc:hsqldb:mem:.",
                "spring.datasource.username=sa",
                "spring.datasource.password=",
                "spring.flyway.enabled=true",
                "spring.flyway.locations=classpath:/db/migrations/hsql",
                "spring.data.jpa.repositories.enabled=true",
                "spring.jpa.show-sql=true"
        },
        classes = IntegrationTestContextConfiguration.class)
public abstract class AbstractServiceIntegrationTest {

}
