package br.com.rk.services;

import br.com.rk.entities.ProjectEntity;
import br.com.rk.repositories.ProjectRepository;
import br.com.rk.services.exception.ServiceException;
import br.com.rk.services.factory.ProjectEntityFactory;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.internal.util.reflection.Whitebox;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * @author Rhuan Karlus
 * @since 04/04/19
 */
@SuppressWarnings("unchecked")
public class AbstractCrudServiceTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    private AbstractCrudService sut;

    @Before
    public void setUp() {
        sut = mock(AbstractCrudService.class);
    }

    @Test
    public void should_throw_exception_when_id_is_null() throws ServiceException {
        exceptionRule.expect(ServiceException.class);
        exceptionRule.expectMessage("The ID can't be null.");

        doCallRealMethod()
                .when(sut)
                .findById(anyLong());

        sut.findById(null);
    }

    @Test
    public void should_throw_exception_when_entity_not_found() throws ServiceException {
        exceptionRule.expect(ServiceException.class);
        exceptionRule.expectMessage("Entity with ID 1 not found.");

        final Long id = 1L;

        final ProjectRepository mockRepo = mock(ProjectRepository.class);
        when(mockRepo.findById(id)).thenReturn(Optional.empty());
        Whitebox.setInternalState(sut, "projectRepository", mockRepo);

        doCallRealMethod()
                .when(sut)
                .findById(id);

        sut.findById(id);
    }

    @Test
    public void should_return_correct_entity_by_id() throws ServiceException {
        final Long id = 1L;
        final ProjectEntity projectEntity = ProjectEntityFactory.buildSimpleEntityWithId(id);

        final ProjectRepository mockRepo = mock(ProjectRepository.class);
        when(mockRepo.findById(id)).thenReturn(Optional.of(projectEntity));
        Whitebox.setInternalState(sut, "projectRepository", mockRepo);

        doCallRealMethod()
                .when(sut)
                .findById(id);

        assertNotNull("It shouldn't return null entity.", sut.findById(id));
        assertEquals("It should return the entity with id " + id, id, sut.findById(id).getId());
    }

}
