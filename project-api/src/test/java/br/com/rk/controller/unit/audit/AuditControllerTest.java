package br.com.rk.controller.unit.audit;

import br.com.rk.controller.audit.AuditController;
import br.com.rk.controller.audit.dto.AuditDTO;
import br.com.rk.controller.dto.ProjectResponse;
import br.com.rk.controller.unit.AbstractCrudControllerTest;
import br.com.rk.converters.ConverterException;
import br.com.rk.entities.audit.Audit;
import br.com.rk.entities.audit.AuditType;
import br.com.rk.services.exception.EntityNotFoundException;
import br.com.rk.services.exception.ServiceException;
import br.com.rk.util.PageFactory;
import br.com.rk.util.builders.AuditBuilder;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
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
    public void should_return_error_500_when_find_all_service_throw_exception() throws Exception {
        final String exceptionText = "Some creepy exception";

        when(mockService.findAll(any(Pageable.class))).thenThrow(new ServiceException(exceptionText));

        final String expectedError = asJsonString(ProjectResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, exceptionText));
        final String findAllAuditResponse = doGetExpectStatus("/audit", HttpStatus.INTERNAL_SERVER_ERROR);

        JSONAssert.assertEquals(expectedError, findAllAuditResponse, false);

        verify(mockService, times(1)).findAll(any(Pageable.class));
    }

    @Test
    public void should_return_error_500_when_find_all_converter_throw_exception_on_convert_to_dto() throws Exception {
        final String exceptionText = "Some creepy exception";

        when(mockService.findAll(any(Pageable.class))).thenReturn(PageFactory.buildPage(5, () -> null));
        when(mockConversor.toDTO(any())).thenThrow(new ConverterException(exceptionText));

        final String expectedError = asJsonString(ProjectResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, exceptionText));
        final String findAllAuditResponse = doGetExpectStatus("/audit", HttpStatus.INTERNAL_SERVER_ERROR);

        JSONAssert.assertEquals(expectedError, findAllAuditResponse, false);

        verify(mockService, times(1)).findAll(any(Pageable.class));
        verify(mockConversor, times(1)).toDTO(any());
    }

    @Test
    public void should_find_all_paginated() throws Exception {
        final Audit audit = AuditBuilder
                .initEntity()
                .url("/someUrl/somePath/test")
                .dateTime(LocalDateTime.now())
                .type(AuditType.INFO)
                .content(null)
                .build();

        final AuditDTO auditDTO = AuditBuilder
                .initDTO()
                .url(audit.getUrl())
                .type(audit.getType())
                .dateTime(audit.getDateTime())
                .content(audit.getContent())
                .build();

        final Page<Audit> auditPage = PageFactory.buildPage(5, () -> audit);
        when(mockService.findAll(any(Pageable.class))).thenReturn(auditPage);
        when(mockConversor.toDTO(any(Audit.class))).thenReturn(auditDTO);

        final String getResponse = doGetExpectStatus("/audit", HttpStatus.OK);
        final String expectedResponse = asJsonString(
                ProjectResponse.ok(Arrays.asList(auditDTO, auditDTO, auditDTO, auditDTO, auditDTO), auditPage));

        JSONAssert.assertEquals(expectedResponse, getResponse, false);

        verify(mockService, times(1)).findAll(any(Pageable.class));
        verify(mockConversor, times(5)).toDTO(any(Audit.class));
    }

    @Test
    public void should_return_error_500_when_find_all_by_example_conversor_throw_exception_to_entity() throws Exception {
        final String exceptionText = "Some creepy exception";

        when(mockConversor.toEntity(any(AuditDTO.class))).thenThrow(new ConverterException(exceptionText));

        final String expectedError = asJsonString(ProjectResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, exceptionText));
        final String findByExampleAuditResponse = doPostExpectStatus("/audit/filter", HttpStatus.INTERNAL_SERVER_ERROR, AuditBuilder.initDTO().build());

        JSONAssert.assertEquals(expectedError, findByExampleAuditResponse, false);

        verify(mockConversor, times(1)).toEntity(any(AuditDTO.class));
    }

    @Test
    public void should_return_error_500_when_find_all_by_example_service_throw_exception() throws Exception {
        final String exceptionText = "Some creepy exception";

        when(mockConversor.toEntity(any(AuditDTO.class))).thenReturn(AuditBuilder.initEntity().build());
        when(mockService.findByExample(any(Audit.class), any(Pageable.class))).thenThrow(new ServiceException(exceptionText));

        final String expectedError = asJsonString(ProjectResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, exceptionText));
        final String findByExampleAuditResponse = doPostExpectStatus("/audit/filter", HttpStatus.INTERNAL_SERVER_ERROR, AuditBuilder.initDTO().build());

        JSONAssert.assertEquals(expectedError, findByExampleAuditResponse, false);

        verify(mockConversor, times(1)).toEntity(any(AuditDTO.class));
        verify(mockService, times(1)).findByExample(any(Audit.class), any(Pageable.class));
    }

    @Test
    public void should_return_error_500_when_find_all_by_example_conversor_throw_exception_to_dto() throws Exception {
        final String exceptionText = "Some creepy exception";

        when(mockConversor.toEntity(any(AuditDTO.class))).thenReturn(AuditBuilder.initEntity().build());
        when(mockService.findByExample(any(Audit.class), any(Pageable.class))).thenReturn(PageFactory.buildPage(1, Audit::new));
        when(mockConversor.toDTO(any(Audit.class))).thenThrow(new ConverterException(exceptionText));

        final String expectedError = asJsonString(ProjectResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, exceptionText));
        final String findByExampleAuditResponse = doPostExpectStatus("/audit/filter", HttpStatus.INTERNAL_SERVER_ERROR, AuditBuilder.initDTO().build());

        JSONAssert.assertEquals(expectedError, findByExampleAuditResponse, false);

        verify(mockConversor, times(1)).toEntity(any(AuditDTO.class));
        verify(mockService, times(1)).findByExample(any(Audit.class), any(Pageable.class));
        verify(mockConversor, times(1)).toDTO(any(Audit.class));
    }

    @Test
    public void should_return_no_content_204_when_find_by_example_returns_nothing() throws Exception {
        final String exceptionText = "No entities found";

        when(mockConversor.toEntity(any(AuditDTO.class))).thenReturn(AuditBuilder.initEntity().build());
        when(mockService.findByExample(any(Audit.class), any(Pageable.class))).thenThrow(new EntityNotFoundException(exceptionText));

        final String expectedError = asJsonString(ProjectResponse.error(HttpStatus.NO_CONTENT, exceptionText));
        final String findByExampleAuditResponse = doPostExpectStatus("/audit/filter", HttpStatus.NO_CONTENT, AuditBuilder.initDTO().build());

        JSONAssert.assertEquals(expectedError, findByExampleAuditResponse, false);

        verify(mockConversor, times(1)).toEntity(any(AuditDTO.class));
        verify(mockService, times(1)).findByExample(any(Audit.class), any(Pageable.class));
    }

    @Test
    public void should_return_page_when_find_all_by_example_return_successfull() throws Exception {
        final Audit audit = AuditBuilder
                .initEntity()
                .url("/someUrl/somePath/test")
                .dateTime(LocalDateTime.now())
                .type(AuditType.INFO)
                .content(null)
                .build();

        final AuditDTO auditDTO = AuditBuilder
                .initDTO()
                .url(audit.getUrl())
                .type(audit.getType())
                .dateTime(audit.getDateTime())
                .content(audit.getContent())
                .build();

        final Page<Audit> auditPage = PageFactory.buildPage(5, () -> audit);
        when(mockConversor.toEntity(any(AuditDTO.class))).thenReturn(AuditBuilder.initEntity().build());
        when(mockService.findByExample(any(Audit.class), any(Pageable.class))).thenReturn(auditPage);
        when(mockConversor.toDTO(any(Audit.class))).thenReturn(auditDTO);

        final String getResponse = doPostExpectStatus("/audit/filter", HttpStatus.OK, auditDTO);
        final String expectedResponse = asJsonString(
                ProjectResponse.ok(Arrays.asList(auditDTO, auditDTO, auditDTO, auditDTO, auditDTO), auditPage));

        JSONAssert.assertEquals(expectedResponse, getResponse, false);

        verify(mockConversor, times(1)).toEntity(any(AuditDTO.class));
        verify(mockService, times(1)).findByExample(any(Audit.class), any(Pageable.class));
        verify(mockConversor, times(5)).toDTO(any(Audit.class));
    }

    @Test
    public void should_return_error_500_when_find_by_id_service_throw_exception() throws Exception {
        final String exceptionText = "Some creepy exception";
        final long entityId = 1L;

        when(mockService.findById(entityId)).thenThrow(new ServiceException(exceptionText));

        final String expectedError = asJsonString(ProjectResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, exceptionText));
        final String findByIdAuditResponse = doGetExpectStatus("/audit/" + entityId, HttpStatus.INTERNAL_SERVER_ERROR);

        JSONAssert.assertEquals(expectedError, findByIdAuditResponse, false);

        verify(mockService, times(1)).findById(entityId);
    }

    @Test
    public void should_return_error_500_when_find_by_id_conversor_throw_exception_to_dto() throws Exception {
        final String exceptionText = "Some creepy exception";
        final long entityId = 1L;

        when(mockService.findById(entityId)).thenReturn(AuditBuilder.initEntity().build());
        when(mockConversor.toDTO(any(Audit.class))).thenThrow(new ConverterException(exceptionText));

        final String expectedError = asJsonString(ProjectResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, exceptionText));
        final String findByIdAuditResponse = doGetExpectStatus("/audit/" + entityId, HttpStatus.INTERNAL_SERVER_ERROR);

        JSONAssert.assertEquals(expectedError, findByIdAuditResponse, false);

        verify(mockService, times(1)).findById(entityId);
        verify(mockConversor, times(1)).toDTO(any(Audit.class));
    }

    @Test
    public void should_return_no_content_204_when_find_by_id_find_nothing() throws Exception {
        final String exceptionText = "Entity not found";
        final long entityId = 1L;

        when(mockService.findById(anyLong())).thenThrow(new EntityNotFoundException(exceptionText));

        final String expectedNoContentResponse = asJsonString(ProjectResponse.error(HttpStatus.NO_CONTENT, exceptionText));
        final String findByIdAuditResponse = doGetExpectStatus("/audit/" + entityId, HttpStatus.NO_CONTENT);

        JSONAssert.assertEquals(expectedNoContentResponse, findByIdAuditResponse, false);

        verify(mockService, times(1)).findById(entityId);
    }

    @Test
    public void should_return_page_when_find_by_id_return_successfull() throws Exception {
        final long entityId = 1L;

        final Audit audit = AuditBuilder
                .initEntity()
                .id(entityId)
                .url("/someUrl/somePath/test")
                .dateTime(LocalDateTime.now())
                .type(AuditType.INFO)
                .content(null)
                .build();

        final AuditDTO auditDTO = AuditBuilder
                .initDTO()
                .url(audit.getUrl())
                .type(audit.getType())
                .dateTime(audit.getDateTime())
                .content(audit.getContent())
                .build();

        when(mockService.findById(entityId)).thenReturn(audit);
        when(mockConversor.toDTO(audit)).thenReturn(auditDTO);

        final String getResponse = doGetExpectStatus("/audit/" + entityId, HttpStatus.OK);
        final String expectedResponse = asJsonString(ProjectResponse.ok(auditDTO));

        JSONAssert.assertEquals(expectedResponse, getResponse, false);

        verify(mockService, times(1)).findById(entityId);
        verify(mockConversor, times(1)).toDTO(audit);
    }

    @Test
    public void should_return_error_500_when_persist_conversor_to_entity_throw_exception() throws Exception {
        final String exceptionText = "Some creepy exception";

        when(mockConversor.toEntity(any(AuditDTO.class))).thenThrow(new ConverterException(exceptionText));

        final String expectedError = asJsonString(ProjectResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, exceptionText));
        final String putAuditResponse = doPutExpectStatus("/audit", HttpStatus.INTERNAL_SERVER_ERROR, AuditBuilder.initDTO().build());

        JSONAssert.assertEquals(expectedError, putAuditResponse, false);

        verify(mockConversor, times(1)).toEntity(any(AuditDTO.class));
    }

    @Test
    public void should_return_error_500_when_persist_service_throw_exception() throws Exception {
        final String exceptionText = "Some creepy exception";
        final Audit audit = AuditBuilder.initEntity().build();
        final AuditDTO auditDTO = AuditBuilder.initDTO().build();

        when(mockConversor.toEntity(any(AuditDTO.class))).thenReturn(audit);
        when(mockService.persist(any(Audit.class))).thenThrow(new ServiceException(exceptionText));

        final String expectedError = asJsonString(ProjectResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, exceptionText));
        final String persistAuditResponse = doPutExpectStatus("/audit", HttpStatus.INTERNAL_SERVER_ERROR, auditDTO);

        JSONAssert.assertEquals(expectedError, persistAuditResponse, false);

        verify(mockConversor, times(1)).toEntity(any(AuditDTO.class));
        verify(mockService, times(1)).persist(audit);
    }

    @Test
    public void should_return_error_500_when_persist_conversor_throw_exception_to_dto() throws Exception {
        final String exceptionText = "Some creepy exception";
        final Audit audit = AuditBuilder.initEntity().build();
        final AuditDTO auditDTO = AuditBuilder.initDTO().build();

        when(mockConversor.toEntity(any(AuditDTO.class))).thenReturn(audit);
        when(mockService.persist(any(Audit.class))).thenReturn(audit);
        when(mockConversor.toDTO(any(Audit.class))).thenThrow(new ConverterException(exceptionText));

        final String expectedError = asJsonString(ProjectResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, exceptionText));
        final String persistAuditResponse = doPutExpectStatus("/audit", HttpStatus.INTERNAL_SERVER_ERROR, auditDTO);

        JSONAssert.assertEquals(expectedError, persistAuditResponse, false);

        verify(mockConversor, times(1)).toEntity(any(AuditDTO.class));
        verify(mockService, times(1)).persist(audit);
        verify(mockConversor, times(1)).toDTO(audit);
    }

    @Test
    public void should_return_entity_when_persist_return_successfull() throws Exception {
        final long entityId = 1L;

        final Audit audit = AuditBuilder
                .initEntity()
                .id(entityId)
                .url("/someUrl/somePath/test")
                .dateTime(LocalDateTime.now())
                .type(AuditType.INFO)
                .content(null)
                .build();

        final AuditDTO auditDTO = AuditBuilder
                .initDTO()
                .url(audit.getUrl())
                .type(audit.getType())
                .dateTime(audit.getDateTime())
                .content(audit.getContent())
                .build();

        when(mockConversor.toEntity(any(AuditDTO.class))).thenReturn(audit);
        when(mockService.persist(audit)).thenReturn(audit);
        when(mockConversor.toDTO(audit)).thenReturn(auditDTO);

        final String persistAuditResponse = doPutExpectStatus("/audit", HttpStatus.OK, auditDTO);
        final String expectedResponse = asJsonString(ProjectResponse.ok(auditDTO));

        JSONAssert.assertEquals(expectedResponse, persistAuditResponse, false);

        verify(mockConversor, times(1)).toEntity(any(AuditDTO.class));
        verify(mockService, times(1)).persist(audit);
        verify(mockConversor, times(1)).toDTO(audit);
    }

    @Test
    public void should_return_error_500_when_delete_service_throw_exception() throws Exception {
        final String exceptionText = "Some creepy exception";
        final long entityId = 1L;

        doThrow(new ServiceException(exceptionText)).when(mockService).delete(entityId);

        final String expectedError = asJsonString(ProjectResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, exceptionText));
        final String deleteAuditResponse = doDeleteExpectStatus("/audit/" + entityId, HttpStatus.INTERNAL_SERVER_ERROR);

        JSONAssert.assertEquals(expectedError, deleteAuditResponse, false);

        verify(mockService, times(1)).delete(entityId);
    }

    @Test
    public void should_return_ok_when_delete_return_successfull() throws Exception {
        final long entityId = 1L;

        doNothing().when(mockService).delete(entityId);

        final String expectedResponse = "";
        final String deleteAuditResponse = doDeleteExpectStatus("/audit/" + entityId, HttpStatus.OK);

        JSONAssert.assertEquals(expectedResponse, deleteAuditResponse, false);

        verify(mockService, times(1)).delete(entityId);
    }

}
