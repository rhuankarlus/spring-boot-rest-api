package br.com.rk.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.MultiValueMap;

import static br.com.rk.util.json.JsonCreator.asJsonString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Rhuan Karlus
 * @since 11/04/2019
 */
@RunWith(SpringRunner.class)
@EnableSpringDataWebSupport
@TestPropertySource(locations = "classpath:application-test.properties")
public abstract class AbstractControllerTest {

    private static final String PAGE_HEADER = "page";
    private static final String PAGE_SIZE_HEADER = "size";

    @Autowired
    protected MockMvc mockMvc;

    protected String doGetExpectStatus(final String url, final HttpStatus status) throws Exception {
        return doGetExpectStatus(url, null, status, null, null);
    }

    protected String doGetExpectStatus(final String url, final MultiValueMap<String, String> params,
                                       final HttpStatus status, Integer page, Integer pageSize) throws Exception {
        return new ObjectMapper().readTree(
                this.mockMvc
                        .perform(get(url).params(insertPageData(params, page, pageSize)))
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

    protected MultiValueMap<String, String> insertPageData() {
        return insertPageData(null, null, null);
    }

    protected MultiValueMap<String, String> insertPageData(MultiValueMap<String, String> params, Integer page,
                                                           Integer pageSize) {
        if (params == null) {
            params = new HttpHeaders();
        }

        params.add(PAGE_HEADER, page == null || page < 0 ? "0" : String.valueOf(page));
        params.add(PAGE_SIZE_HEADER, pageSize == null || pageSize < 1 ? "1" : String.valueOf(pageSize));

        return params;
    }

}
