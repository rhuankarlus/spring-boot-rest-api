package br.com.rk.services.user.impl;

import br.com.rk.entities.user.Permission;
import br.com.rk.entities.user.User;
import br.com.rk.services.exception.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Rhuan Karlus
 * @since 04/04/19
 */
public class PermissionCrudServiceImplTest {

    private PermissionCrudServiceImpl sut;

    @BeforeEach
    public void setUp() {
        sut = new PermissionCrudServiceImpl();
    }

    @Test
    public void should_throw_exception_when_user_is_null() {
        final ServiceException expectedException =
                assertThrows(ServiceException.class, () -> sut.findByExample(null, null));

        assertEquals("The entity object can't be null.", expectedException.getMessage());
    }

    @Test
    public void should_throw_exception_when_pageable_is_null() {
        final ServiceException expectedException =
                assertThrows(ServiceException.class, () -> sut.findByExample(new Permission(), null));

        assertEquals("The pageable object can't be null.", expectedException.getMessage());
    }

}
