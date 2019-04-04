package br.com.rk.services.audit.impl;

import br.com.rk.entities.audit.Audit;
import br.com.rk.services.exception.ServiceException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author Rhuan Karlus
 * @since 04/04/19
 */
public class AuditCrudServiceImplTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    private AuditCrudServiceImpl sut;

    @Before
    public void setUp() throws Exception {
        sut = new AuditCrudServiceImpl();
    }

    @Test
    public void should_throw_exception_when_audit_is_null() throws ServiceException {
        exceptionRule.expect(ServiceException.class);
        exceptionRule.expectMessage("The audit object can't be null.");

        sut.validateParams(null, null);
    }

    @Test
    public void should_throw_exception_when_pageable_is_null() throws ServiceException {
        exceptionRule.expect(ServiceException.class);
        exceptionRule.expectMessage("The pageable object can't be null.");

        sut.validateParams(new Audit(), null);
    }

}
