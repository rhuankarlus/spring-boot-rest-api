package br.com.rk.services.audit.impl;

import br.com.rk.entities.audit.Audit;
import br.com.rk.repositories.audit.AuditRepository;
import br.com.rk.services.ProjectCrudService;
import br.com.rk.services.exception.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Rhuan Karlus
 * @since 04/04/19
 */
public class AuditCrudServiceImplTest {

    private AuditCrudServiceImpl sut;

    @BeforeEach
    public void setUp() {
        sut = new AuditCrudServiceImpl(Mockito.mock(AuditRepository.class));
    }

    @Test
    public void should_throw_exception_when_audit_is_null() {
        final ServiceException expectedException =
                assertThrows(ServiceException.class, () -> sut.findByExample(null, null));

        assertEquals("The entity object can't be null.", expectedException.getMessage());
    }

    @Test
    public void should_throw_exception_when_pageable_is_null() {
        final ServiceException expectedException =
                assertThrows(ServiceException.class, () -> sut.findByExample(new Audit(), null));

        assertEquals("The pageable object can't be null.", expectedException.getMessage());
    }

}
