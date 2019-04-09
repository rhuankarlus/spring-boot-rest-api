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
    public void should_throw_exception_when_entity_not_found() throws ServiceException {
        exceptionRule.expect(ServiceException.class);
        exceptionRule.expectMessage("Entity with ID 1 not found.");

        final long id = 1L;

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
        final long id = 1L;
        final ProjectEntity projectEntity = ProjectEntityFactory.buildSimpleEntityWithId(id);

        final ProjectRepository mockRepo = mock(ProjectRepository.class);
        when(mockRepo.findById(id)).thenReturn(Optional.of(projectEntity));
        Whitebox.setInternalState(sut, "projectRepository", mockRepo);

        doCallRealMethod()
                .when(sut)
                .findById(id);

        assertNotNull("It shouldn't return null entity.", sut.findById(id));
        assertEquals("It should return the entity with id " + id, Long.valueOf(id), sut.findById(id).getId());
    }

    @Test
    public void should_throw_exception_when_entity_to_persist_is_null() throws ServiceException {
        exceptionRule.expect(ServiceException.class);
        exceptionRule.expectMessage("The entity shouldn't be null.");

        doCallRealMethod()
                .when(sut)
                .persist(any());

        sut.persist(null);
    }

    @Test
    public void should_throw_exception_when_persist_entity_throws_exception() throws ServiceException {
        exceptionRule.expect(ServiceException.class);
        exceptionRule.expectMessage("Error trying to persist entity");

        final ProjectEntity projectEntity = ProjectEntityFactory.buildSimpleEntityWithId(1L);
        final ProjectRepository mockRepo = mock(ProjectRepository.class);
        when(mockRepo.save(projectEntity)).thenThrow(Exception.class);
        Whitebox.setInternalState(sut, "projectRepository", mockRepo);

        doCallRealMethod()
                .when(sut)
                .persist(projectEntity);

        sut.persist(projectEntity);
    }

    @Test
    public void should_return_correct_entity_when_persist() throws ServiceException {
        final Long id = 1L;

        final ProjectEntity projectEntity = ProjectEntityFactory.buildSimpleEntityWithoutId();
        final ProjectEntity projectEntityWithId = ProjectEntityFactory.buildSimpleEntityWithId(id);

        final ProjectRepository mockRepo = mock(ProjectRepository.class);
        when(mockRepo.save(projectEntity)).thenReturn(projectEntityWithId);
        Whitebox.setInternalState(sut, "projectRepository", mockRepo);

        doCallRealMethod()
                .when(sut)
                .persist(projectEntity);

        assertNotNull("It shouldn't return a null entity.", sut.persist(projectEntity));
        assertEquals("It should return the entity with id " + id, id, sut.persist(projectEntity).getId());
    }

    @Test
    public void should_throw_exception_when_delete_fail() throws ServiceException {
        exceptionRule.expect(ServiceException.class);
        exceptionRule.expectMessage("Error trying to delete the entity");

        final long id = 1L;
        final ProjectRepository mockRepo = mock(ProjectRepository.class);
        doThrow(Exception.class).when(mockRepo).deleteById(isA(Long.class));
        Whitebox.setInternalState(sut, "projectRepository", mockRepo);

        doCallRealMethod()
                .when(sut)
                .delete(id);

        sut.delete(id);

        verify(mockRepo).deleteById(id);
    }

    @Test
    public void should_delete_entity_correctly() throws ServiceException {
        final long id = 1L;

        final ProjectRepository mockRepo = mock(ProjectRepository.class);
        doNothing().when(mockRepo).deleteById(id);
        Whitebox.setInternalState(sut, "projectRepository", mockRepo);

        doCallRealMethod()
                .when(sut)
                .delete(id);

        sut.delete(id);

        verify(mockRepo).deleteById(id);
    }

}
