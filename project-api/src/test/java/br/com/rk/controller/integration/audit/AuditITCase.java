package br.com.rk.controller.integration.audit;

import br.com.rk.controller.audit.dto.AuditDTO;
import br.com.rk.controller.dto.ProjectResponse;
import br.com.rk.controller.integration.AbstractControllerIntegrationTest;
import br.com.rk.entities.audit.AuditType;
import br.com.rk.util.builders.AuditBuilder;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;

import static br.com.rk.util.json.JsonCreator.asJsonString;

/**
 * @author Rhuan Karlus
 * @since 15/04/2019
 */
public class AuditITCase extends AbstractControllerIntegrationTest {

    @Test
    public void should_return_first_element_correctly_find_all() throws Exception {
        final AuditDTO expectedAuditDTO = AuditBuilder
                .initDTO()
                .url("/path1/path2/path3")
                .content("some content 1")
                .type(AuditType.ERROR)
                .dateTime(LocalDateTime.parse("2018-12-23T15:46:13", DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .build();

        final String expectedResponse = asJsonString(ProjectResponse.of(
                null,
                Collections.singletonList(expectedAuditDTO),
                new ProjectResponse.Pagination(0, 1, 6, 6, Sort.unsorted())));

        final String getAuditsResponse = doGetExpectStatus("/audit", HttpStatus.OK);

        JSONAssert.assertEquals(expectedResponse, getAuditsResponse, false);
    }

    @Test
    public void should_return_all_elements_correctly_find_all() throws Exception {
        final AuditDTO audit1 = AuditBuilder
                .initDTO()
                .url("/path1/path2/path3")
                .content("some content 1")
                .type(AuditType.ERROR)
                .dateTime(LocalDateTime.parse("2018-12-23T15:46:13", DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .build();

        final AuditDTO audit2 = AuditBuilder
                .initDTO()
                .url("/path1/path4/path5")
                .content("some content 2")
                .type(AuditType.ERROR)
                .dateTime(LocalDateTime.parse("2019-01-02T09:30:13", DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .build();

        final AuditDTO audit3 = AuditBuilder
                .initDTO()
                .url("/path1/path2/path3")
                .content("some content 3")
                .type(AuditType.INFO)
                .dateTime(LocalDateTime.parse("2019-01-02T10:15:55", DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .build();

        final AuditDTO audit4 = AuditBuilder
                .initDTO()
                .url("/path6/path7")
                .content("some content 4")
                .type(AuditType.INFO)
                .dateTime(LocalDateTime.parse("2019-02-15T14:00:55", DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .build();

        final AuditDTO audit5 = AuditBuilder
                .initDTO()
                .url("/path8")
                .content("some content 5")
                .type(AuditType.INFO)
                .dateTime(LocalDateTime.parse("2019-02-22T21:02:47", DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .build();

        final AuditDTO audit6 = AuditBuilder
                .initDTO()
                .url("/")
                .content("some content 6")
                .type(AuditType.ERROR)
                .dateTime(LocalDateTime.parse("2019-04-18T05:52:21", DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .build();

        final String expectedResponse = asJsonString(ProjectResponse.of(
                null,
                Arrays.asList(audit1, audit2, audit3, audit4, audit5, audit6),
                new ProjectResponse.Pagination(0, 6, 6, 1, Sort.unsorted())));

        final String getAuditsResponse = doGetExpectStatus("/audit", HttpStatus.OK, 0, 6);

        JSONAssert.assertEquals(expectedResponse, getAuditsResponse, false);
    }

}
