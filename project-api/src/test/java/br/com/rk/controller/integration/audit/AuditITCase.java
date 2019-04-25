package br.com.rk.controller.integration.audit;

import br.com.rk.controller.audit.dto.AuditDTO;
import br.com.rk.controller.dto.ProjectResponse;
import br.com.rk.controller.integration.AbstractControllerIntegrationTest;
import br.com.rk.entities.audit.AuditType;
import br.com.rk.util.PageFactory;
import br.com.rk.util.builders.AuditBuilder;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;

import static br.com.rk.util.json.JsonCreator.asJsonString;
import static br.com.rk.util.PageFactory.*;

/**
 * @author Rhuan Karlus
 * @since 15/04/2019
 */
public class AuditITCase extends AbstractControllerIntegrationTest {

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        final AuditDTO expectedAuditDTO = AuditBuilder
                .initDTO()
                .url("/path1/path2/path3")
                .content("some content 1")
                .type(AuditType.ERROR)
                .dateTime(LocalDateTime.parse("2018-12-23T15:46:13", DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .build();

        final String expectedResponse = asJsonString(ProjectResponse.ok(
                Collections.singletonList(expectedAuditDTO),
                buildPage(buildPageable(0, 6), 1, () -> expectedAuditDTO)));

        final String getAuditsResponse = doGetExpectStatus("/audit", HttpStatus.OK);

        JSONAssert.assertEquals(expectedResponse, getAuditsResponse, false);
    }

}
