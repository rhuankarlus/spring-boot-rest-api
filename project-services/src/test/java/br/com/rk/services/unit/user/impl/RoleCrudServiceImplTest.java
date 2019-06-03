package br.com.rk.services.unit.user.impl;

import br.com.rk.entities.user.Role;
import br.com.rk.repositories.user.RoleRepository;
import br.com.rk.services.exception.ServiceException;
import br.com.rk.services.user.impl.RoleCrudServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Rhuan Karlus
 * @since 04/04/19
 */
public class RoleCrudServiceImplTest {

    private RoleCrudServiceImpl sut;

    @BeforeEach
    public void setUp() {
        sut = new RoleCrudServiceImpl(Mockito.mock(RoleRepository.class));
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
                assertThrows(ServiceException.class, () -> sut.findByExample(new Role(), null));

        assertEquals("The pageable object can't be null.", expectedException.getMessage());
    }

}