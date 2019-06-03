package br.com.rk.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.MultiValueMap;

import static br.com.rk.util.json.JsonCreator.asJsonString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Rhuan Karlus
 * @since 11/04/2019
 */
@EnableSpringDataWebSupport
@ExtendWith(SpringExtension.class)
public abstract class AbstractControllerTest {

    private static final String PAGE_PARAM = "page";
    private static final String PAGE_SIZE_PARAM = "size";
    private static final String SORT_PARAM = "sort";

    @Autowired
    protected MockMvc mockMvc;

    protected String doGetExpectStatus(final String url, final HttpStatus status) throws Exception {
        return doGetExpectStatus(url, null, status, null, null);
    }

    protected String doGetExpectStatus(final String url, final HttpStatus status, Integer page, Integer pageSize) throws Exception {
        return doGetExpectStatus(url, null, status, page, pageSize);
    }

    protected String doGetExpectStatus(final String url, final MultiValueMap<String, String> params,
                                       final HttpStatus status, Integer page, Integer pageSize) throws Exception {
        return doGetExpectStatus(url, status, insertPageData(params, page, pageSize));
    }

    protected String doGetExpectStatus(final String url, final HttpStatus status,
                                       MultiValueMap<String, String> params) throws Exception {
        if (params == null) {
            params = new HttpHeaders();
        }

        return new ObjectMapper().readTree(
                this.mockMvc
                        .perform(get(url).params(params))
                        .andDo(print())
                        .andExpect(status().is(status.value()))
                        .andReturn()
                        .getResponse()
                        .getContentAsString())
                .toString();
    }

    protected String doPostExpectStatus(final String url, final HttpStatus status, final Object payload) throws Exception {
        return doPostExpectStatus(url, new HttpHeaders(), status, payload);
    }

    protected String doPostExpectStatus(final String url, final MultiValueMap<String, String> params,
                                        final HttpStatus status, final Object payload) throws Exception {
        return new ObjectMapper().readTree(
                this.mockMvc
                        .perform(post(url)
                                .params(params)
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(asJsonString(payload)))
                        .andDo(print())
                        .andExpect(status().is(status.value()))
                        .andReturn()
                        .getResponse()
                        .getContentAsString())
                .toString();
    }

    protected String doPutExpectStatus(final String url, final HttpStatus status, final Object payload) throws Exception {
        return doPutExpectStatus(url, new HttpHeaders(), status, payload);
    }

    protected String doPutExpectStatus(final String url, final MultiValueMap<String, String> params,
                                       final HttpStatus status, final Object payload) throws Exception {
        return new ObjectMapper().readTree(
                this.mockMvc
                        .perform(put(url)
                                .params(params)
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(asJsonString(payload)))
                        .andDo(print())
                        .andExpect(status().is(status.value()))
                        .andReturn()
                        .getResponse()
                        .getContentAsString())
                .toString();
    }

    protected String doDeleteExpectStatus(final String url, final HttpStatus status) throws Exception {
        return doDeleteExpectStatus(url, null, status);
    }

    protected String doDeleteExpectStatus(final String url, final MultiValueMap<String, String> params,
                                          final HttpStatus status) throws Exception {
        final JsonNode response = new ObjectMapper().readTree(
                this.mockMvc
                        .perform(delete(url).params(insertPageData(params, null, null)))
                        .andDo(print())
                        .andExpect(status().is(status.value()))
                        .andReturn()
                        .getResponse()
                        .getContentAsString());

        return response == null ? "" : response.toString();
    }

    protected MultiValueMap<String, String> insertPageData() {
        return insertPageData(null, null, null);
    }

    protected MultiValueMap<String, String> insertPageData(MultiValueMap<String, String> params, Integer page,
                                                           Integer pageSize) {
        if (params == null) {
            params = new HttpHeaders();
        }

        params.add(PAGE_PARAM, page == null || page < 0 ? "0" : String.valueOf(page));
        params.add(PAGE_SIZE_PARAM, pageSize == null || pageSize < 1 ? "1" : String.valueOf(pageSize));

        return params;
    }

    protected MultiValueMap<String, String> insertSortData(MultiValueMap<String, String> params, final Sort.Order order) {
        if (params == null) {
            params = new HttpHeaders();
        }

        params.add(SORT_PARAM, order.getProperty() + "," + order.getDirection().name());

        return params;
    }

}

