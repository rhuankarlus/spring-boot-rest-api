package br.com.rk.controller.integration.audit;

import br.com.rk.controller.audit.dto.AuditDTO;
import br.com.rk.controller.dto.ProjectResponse;
import br.com.rk.controller.integration.AbstractControllerIntegrationTest;
import br.com.rk.entities.audit.AuditType;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static br.com.rk.util.DateTimeUtil.getMilliseconds;
import static br.com.rk.util.json.JsonCreator.asJsonString;

/**
 * @author Rhuan Karlus
 * @since 15/04/2019
 */
public class AuditITCase extends AbstractControllerIntegrationTest {

    private static final List<AuditDTO> audits = new ArrayList<AuditDTO>() {{
        add(new AuditDTO()
                .setId(1L)
                .setUrl("/path1/path2/path3")
                .setContent("some content 1")
                .setType(AuditType.ERROR.getCode())
                .setDateTime(getMilliseconds("2018-12-23T15:46:13")));

        add(new AuditDTO()
                .setId(2L)
                .setUrl("/path1/path4/path5")
                .setContent("some content 2")
                .setType(AuditType.ERROR.getCode())
                .setDateTime(getMilliseconds("2019-01-02T09:30:13")));

        add(new AuditDTO()
                .setId(3L)
                .setUrl("/path1/path2/path3")
                .setContent("some content 3")
                .setType(AuditType.INFO.getCode())
                .setDateTime(getMilliseconds("2019-01-02T10:15:55")));

        add(new AuditDTO()
                .setId(4L)
                .setUrl("/path6/path7")
                .setContent("some content 4")
                .setType(AuditType.INFO.getCode())
                .setDateTime(getMilliseconds("2019-02-15T14:00:55")));

        add(new AuditDTO()
                .setId(5L)
                .setUrl("/path8")
                .setContent("some content 5")
                .setType(AuditType.INFO.getCode())
                .setDateTime(getMilliseconds("2019-02-22T21:02:47")));

        add(new AuditDTO()
                .setId(6L)
                .setUrl("/")
                .setContent("some content 6")
                .setType(AuditType.ERROR.getCode())
                .setDateTime(getMilliseconds("2019-04-18T05:52:21")));
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
                new AuditDTO().setUrl("/path6/path7"));

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
                new AuditDTO().setUrl("/"));

        JSONAssert.assertEquals(expectedResponse, findByExampleResponse, false);
    }

    @Test
    public void should_return_204_no_content_when_find_by_example_returns_nothing() throws Exception {
        final String expectedResponse = asJsonString(ProjectResponse.error(
                HttpStatus.NO_CONTENT,
                "No entity found"));

        final String findByExampleResponse = doPostExpectStatus(
                "/audit/filter",
                HttpStatus.NO_CONTENT,
                new AuditDTO().setUrl("3sd1a65d16ad16as1d6sa15d56as1d56sad --- invalid url"));

        JSONAssert.assertEquals(expectedResponse, findByExampleResponse, false);
    }

    @Test
    public void should_return_correct_result_find_by_id() throws Exception {
        final String expectedResponse = asJsonString(ProjectResponse.ok(audits.get(0)));

        final String findByIdResponse = doGetExpectStatus("/audit/" + audits.get(0).getId(), HttpStatus.OK);

        JSONAssert.assertEquals(expectedResponse, findByIdResponse, false);
    }

    @Test
    public void should_return_204_no_content_when_find_by_id_returns_nothing() throws Exception {
        final String expectedResponse = asJsonString(ProjectResponse.error(HttpStatus.NO_CONTENT, "Entity with ID 9999 not found."));

        final String findByIdResponse = doGetExpectStatus("/audit/" + 9999, HttpStatus.NO_CONTENT);

        JSONAssert.assertEquals(expectedResponse, findByIdResponse, false);
    }

    @Test
    public void should_persist_new_entity_from_dto() throws Exception {
        final AuditDTO auditDTO = new AuditDTO()
                .setUrl("/bar/foo/test")
                .setContent("this is a simple content")
                .setType(AuditType.INFO.getCode())
                .setDateTime(getMilliseconds(LocalDateTime.now()));

        final String persistAuditResponse = doPutExpectStatus("/audit", HttpStatus.OK, auditDTO);

        auditDTO.setId(7L);
        final String expectedResponse = asJsonString(ProjectResponse.ok(auditDTO));

        JSONAssert.assertEquals(expectedResponse, persistAuditResponse, false);
    }

    @Test
    public void should_update_entity_from_dto() throws Exception {
        final AuditDTO auditDTO = new AuditDTO()
                .setUrl("/bar/foo/test")
                .setContent("this is a simple content")
                .setType(AuditType.INFO.getCode())
                .setDateTime(getMilliseconds(LocalDateTime.now()));

        doPutExpectStatus("/audit", HttpStatus.OK, auditDTO);

        auditDTO.setId(7L);
        auditDTO.setUrl("/another/url");

        final String updateAuditResponse = doPutExpectStatus("/audit", HttpStatus.OK, auditDTO);
        final String expectedResponse = asJsonString(ProjectResponse.ok(auditDTO));

        JSONAssert.assertEquals(expectedResponse, updateAuditResponse, false);
    }

    @Test
    public void should_delete_entities_correctly() throws Exception {
        for (final AuditDTO auditDTO : audits) {
            doDeleteExpectStatus("/audit/" + auditDTO.getId(), HttpStatus.OK);

            final String expectedResponse = asJsonString(ProjectResponse.error(
                    HttpStatus.NO_CONTENT,
                    "Entity with ID " + auditDTO.getId() + " not found."));

            final String findByIdResponse = doGetExpectStatus("/audit/" + auditDTO.getId(), HttpStatus.NO_CONTENT);

            JSONAssert.assertEquals(expectedResponse, findByIdResponse, false);
        }
    }

}
