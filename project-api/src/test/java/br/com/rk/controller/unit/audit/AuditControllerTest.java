package br.com.rk.controller.unit.audit;

import br.com.rk.controller.audit.AuditController;
import br.com.rk.controller.audit.dto.AuditDTO;
import br.com.rk.controller.dto.ProjectResponse;
import br.com.rk.controller.unit.AbstractCrudControllerTest;
import br.com.rk.entities.audit.Audit;
import br.com.rk.entities.audit.AuditType;
import br.com.rk.services.exception.ServiceException;
import br.com.rk.util.PageFactory;
import br.com.rk.util.builders.AuditEntityBuilder;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;

import static br.com.rk.util.json.JsonCreator.asJsonString;
import static org.mockito.Mockito.*;

/**
 * @author Rhuan Karlus
 * @since 11/04/2019
 */
@WebMvcTest(AuditController.class)
public class AuditControllerTest extends AbstractCrudControllerTest<Audit, AuditDTO> {

    @Test
    public void should_return_error_500_when_service_throw_exception() throws Exception {
        final String exceptionText = "Some creepy exception";

        when(mockService.findAll(any(Pageable.class))).thenThrow(new ServiceException(exceptionText));

        final String expectedError = asJsonString(ProjectResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, exceptionText));
        final String findAllAuditResponse = doGetExpectStatus("/audit", HttpStatus.INTERNAL_SERVER_ERROR);

        JSONAssert.assertEquals(expectedError, findAllAuditResponse, false);

        verify(mockService, times(1)).findAll(any(Pageable.class));
    }

    @Test
    public void should_find_all_paginated() throws Exception {
        final Audit audit = AuditEntityBuilder
                .init()
                .url("/someUrl/somePath/test")
                .dateTime(LocalDateTime.now())
                .type(AuditType.INFO)
                .content(null)
                .build();

        final AuditDTO auditDTO = new AuditDTO();
        auditDTO.setUrl(audit.getUrl());
        auditDTO.setType(audit.getType().getCode());
        auditDTO.setDateTime(audit.getDateTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        auditDTO.setContent(audit.getContent());

        final Page<Audit> auditPage = PageFactory.buildPage(5, () -> audit);
        when(mockService.findAll(any(Pageable.class))).thenReturn(auditPage);
        when(mockConversor.toDTO(any(Audit.class))).thenReturn(auditDTO);

        final String getResponse = doGetExpectStatus("/audit", HttpStatus.OK);
        final String expectedResponse = asJsonString(
                ProjectResponse.ok(Arrays.asList(auditDTO, auditDTO, auditDTO, auditDTO, auditDTO), auditPage));
        ProjectResponse.of(null, auditPage.getContent(), auditPage);

//        createJsonStringArray(auditDTO, 5);

        JSONAssert.assertEquals(expectedResponse, getResponse, false);

        verify(mockService, times(1)).findAll(any(Pageable.class));
        verify(mockConversor, times(5)).toDTO(any(Audit.class));
    }

}
