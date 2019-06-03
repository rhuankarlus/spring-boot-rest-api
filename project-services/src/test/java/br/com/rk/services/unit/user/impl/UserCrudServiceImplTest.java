package br.com.rk.services.unit.user.impl;

import br.com.rk.entities.user.User;
import br.com.rk.repositories.user.UserRepository;
import br.com.rk.services.exception.ServiceException;
import br.com.rk.services.user.impl.UserCrudServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

/**
 * @author Rhuan Karlus
 * @since 04/04/19
 */
public class UserCrudServiceImplTest {

    private UserCrudServiceImpl sut;

    @BeforeEach
    public void setUp() {
        sut = new UserCrudServiceImpl(mock(UserRepository.class), mock(PasswordEncoder.class));
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
                assertThrows(ServiceException.class, () -> sut.findByExample(new User(), null));

        assertEquals("The pageable object can't be null.", expectedException.getMessage());
    }

}
