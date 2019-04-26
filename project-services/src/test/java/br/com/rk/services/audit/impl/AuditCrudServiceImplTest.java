package br.com.rk.services.audit.impl;

import br.com.rk.entities.audit.Audit;
import br.com.rk.services.exception.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        sut = new AuditCrudServiceImpl();
    }

    @Test
    public void should_throw_exception_when_audit_is_null() {
        final ServiceException expectedException =
                assertThrows(ServiceException.class, () -> sut.validateParams(null, null));

        assertEquals("The audit object can't be null.", expectedException.getMessage());
    }

    @Test
    public void should_throw_exception_when_pageable_is_null() {
        final ServiceException expectedException =
                assertThrows(ServiceException.class, () -> sut.validateParams(new Audit(), null));

        assertEquals("The pageable object can't be null.", expectedException.getMessage());
    }

}
