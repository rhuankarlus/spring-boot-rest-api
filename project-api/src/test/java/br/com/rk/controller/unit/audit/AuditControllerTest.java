package br.com.rk.controller.unit.audit;

import br.com.rk.controller.audit.AuditController;
import br.com.rk.controller.audit.dto.AuditDTO;
import br.com.rk.controller.unit.AbstractCrudControllerTest;
import br.com.rk.entities.audit.Audit;
import br.com.rk.entities.audit.AuditType;
import br.com.rk.util.PageFactory;
import br.com.rk.util.builders.AuditEntityBuilder;
import br.com.rk.util.json.JsonCreator;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.mockito.Mockito.*;

/**
 * @author Rhuan Karlus
 * @since 11/04/2019
 */
@WebMvcTest(AuditController.class)
public class AuditControllerTest extends AbstractCrudControllerTest<Audit, AuditDTO> {

    @Test
    public void should_find_all_paginated() throws Exception {
        when(mockService.findAll(any(Pageable.class))).thenReturn(PageFactory.buildSimplePage(() -> AuditEntityBuilder.init().build()));

        final AuditDTO auditDTO = new AuditDTO();
        auditDTO.setUrl("url_teste");
        auditDTO.setType(AuditType.INFO.getCode());
        auditDTO.setDateTime(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        auditDTO.setContent("payload");
        when(mockConversor.toDTO(any(Audit.class))).thenReturn(auditDTO);

        final String getResponse = doGetExpectStatus("/audit", HttpStatus.OK);
        final String expectedResponse = JsonCreator.createJsonStringArray(auditDTO, 5);

        JSONAssert.assertEquals(expectedResponse, getResponse, false);

        verify(mockService, times(1)).findAll(any(Pageable.class));
        verify(mockConversor, times(5)).toDTO(any(Audit.class));
    }

}
