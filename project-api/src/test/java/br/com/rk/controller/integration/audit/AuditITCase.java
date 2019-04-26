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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static br.com.rk.util.json.JsonCreator.asJsonString;

/**
 * @author Rhuan Karlus
 * @since 15/04/2019
 */
public class AuditITCase extends AbstractControllerIntegrationTest {

    private static final List<AuditDTO> audits = new ArrayList<AuditDTO>() {{
        add(AuditBuilder
                .initDTO()
                .id(1L)
                .url("/path1/path2/path3")
                .content("some content 1")
                .type(AuditType.ERROR)
                .dateTime(LocalDateTime.parse("2018-12-23T15:46:13", DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .build());

        add(AuditBuilder
                .initDTO()
                .id(2L)
                .url("/path1/path4/path5")
                .content("some content 2")
                .type(AuditType.ERROR)
                .dateTime(LocalDateTime.parse("2019-01-02T09:30:13", DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .build());

        add(AuditBuilder
                .initDTO()
                .id(3L)
                .url("/path1/path2/path3")
                .content("some content 3")
                .type(AuditType.INFO)
                .dateTime(LocalDateTime.parse("2019-01-02T10:15:55", DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .build());

        add(AuditBuilder
                .initDTO()
                .id(4L)
                .url("/path6/path7")
                .content("some content 4")
                .type(AuditType.INFO)
                .dateTime(LocalDateTime.parse("2019-02-15T14:00:55", DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .build());

        add(AuditBuilder
                .initDTO()
                .id(5L)
                .url("/path8")
                .content("some content 5")
                .type(AuditType.INFO)
                .dateTime(LocalDateTime.parse("2019-02-22T21:02:47", DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .build());

        add(AuditBuilder
                .initDTO()
                .id(6L)
                .url("/")
                .content("some content 6")
                .type(AuditType.ERROR)
                .dateTime(LocalDateTime.parse("2019-04-18T05:52:21", DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .build());
    }};

    @Test
    public void should_return_first_element_correctly_find_all() throws Exception {
        final String expectedResponse = asJsonString(ProjectResponse.of(
                null,
                Collections.singletonList(audits.get(0)),
                new ProjectResponse.Pagination(0, 1, 6, 6, Sort.unsorted())));

        final String getAuditsResponse = doGetExpectStatus("/audit", HttpStatus.OK);

        JSONAssert.assertEquals(expectedResponse, getAuditsResponse, false);
    }

    @Test
    public void should_return_all_elements_correctly_find_all() throws Exception {
        final String expectedResponse = asJsonString(ProjectResponse.of(null, audits,
                new ProjectResponse.Pagination(0, 6, 6, 1, Sort.unsorted())));

        final String getAuditsResponse = doGetExpectStatus("/audit", HttpStatus.OK, 0, 6);

        JSONAssert.assertEquals(expectedResponse, getAuditsResponse, false);
    }

    @Test
    public void should_return_elements_ordered_find_all() throws Exception {
        final String expectedResponse = asJsonString(ProjectResponse.of(
                null,
                Arrays.asList(audits.get(4), audits.get(5)),
                new ProjectResponse.Pagination(
                        0, 2, 6, 3,
                        Sort.by(Sort.Order.desc("dateTime")))));

        final String getAuditsResponse = doGetExpectStatus(
                "/audit",
                HttpStatus.OK,
                insertSortData(insertPageData(null, 0, 2), Sort.Order.desc("dateTime")));

        JSONAssert.assertEquals(expectedResponse, getAuditsResponse, false);
    }

    @Test
    public void should_find_one_by_example() throws Exception {
        final String expectedResponse = asJsonString(ProjectResponse.of(
                null,
                Collections.singletonList(audits.get(3)),
                new ProjectResponse.Pagination(
                        0, 1, 1, 1,
                        Sort.unsorted())));

        final String findByExampleResponse = doPostExpectStatus(
                "/audit/filter",
                HttpStatus.OK,
                AuditBuilder.initDTO().url("/path6/path7").build());

        JSONAssert.assertEquals(expectedResponse, findByExampleResponse, false);
    }

    @Test
    public void should_find_more_than_one_by_example() throws Exception {
        final String expectedResponse = asJsonString(ProjectResponse.of(
                null,
                audits,
                new ProjectResponse.Pagination(
                        0, 6, 6, 1,
                        Sort.unsorted())));

        final String findByExampleResponse = doPostExpectStatus(
                "/audit/filter",
                HttpStatus.OK,
                AuditBuilder.initDTO().url("/").build());

        JSONAssert.assertEquals(expectedResponse, findByExampleResponse, false);
    }

    @Test
    public void should_return_204_no_content_when_find_by_example_returns_nothing() throws Exception {
        final String expectedResponse = asJsonString(ProjectResponse.error(
                HttpStatus.NO_CONTENT,
                "No entity found"));

        for (final AuditDTO auditDTO : audits) {
            doDeleteExpectStatus("/audit/" + auditDTO.getId(), HttpStatus.OK);
        }

        final String findByExampleResponse = doPostExpectStatus(
                "/audit/filter",
                HttpStatus.NO_CONTENT,
                AuditBuilder.initDTO().build());

        JSONAssert.assertEquals(expectedResponse, findByExampleResponse, false);
    }

}
