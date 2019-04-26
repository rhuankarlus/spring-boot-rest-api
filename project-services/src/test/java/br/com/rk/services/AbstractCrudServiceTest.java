package br.com.rk.services;

import br.com.rk.entities.ProjectEntity;
import br.com.rk.repositories.ProjectRepository;
import br.com.rk.services.exception.ServiceException;
import br.com.rk.services.factory.PageFactory;
import br.com.rk.services.factory.ProjectEntityFactory;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
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

        try {
            sut.findById(id);
        } finally {
            verify(mockRepo, times(1)).findById(id);
        }
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

        final ProjectEntity returnedProjectEntity = sut.findById(id);

        assertNotNull("It shouldn't return null entity.", returnedProjectEntity);
        assertEquals("It should return the entity with id " + id, Long.valueOf(id), returnedProjectEntity.getId());

        verify(mockRepo, times(1)).findById(id);
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

        try {
            sut.persist(projectEntity);
        } finally {
            verify(mockRepo, times(1)).save(projectEntity);
        }
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

        final ProjectEntity returnedProjectEntity = sut.persist(projectEntity);

        assertNotNull("It shouldn't return a null entity.", returnedProjectEntity);
        assertEquals("It should return the entity with id " + id, id, returnedProjectEntity.getId());

        verify(mockRepo, times(1)).save(projectEntity);
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

        try {
            sut.delete(id);
        } finally {
            verify(mockRepo, times(1)).deleteById(id);
        }
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

        verify(mockRepo, times(1)).deleteById(id);
    }

    @Test
    public void should_throw_exception_when_find_all_fail() throws ServiceException {
        exceptionRule.expect(ServiceException.class);
        exceptionRule.expectMessage("Error when trying to read the entities list from database");

        final ProjectRepository mockRepo = mock(ProjectRepository.class);
        when(mockRepo.findAll()).thenThrow(Exception.class);
        Whitebox.setInternalState(sut, "projectRepository", mockRepo);

        doCallRealMethod()
                .when(sut)
                .findAll();

        try {
            sut.findAll();
        } finally {
            verify(mockRepo, times(1)).findAll();
        }
    }

    @Test
    public void should_return_result_list_when_find_all_execute_correctly() throws ServiceException {
        final int listSize = 5;
        final List<ProjectEntity> projectEntityList = ProjectEntityFactory.buildSimpleListWithoutId(listSize);

        final ProjectRepository mockRepo = mock(ProjectRepository.class);
        when(mockRepo.findAll()).thenReturn(projectEntityList);
        Whitebox.setInternalState(sut, "projectRepository", mockRepo);

        doCallRealMethod()
                .when(sut)
                .findAll();

        final List<ProjectEntity> returnedProjectEntityList = sut.findAll();

        assertNotNull("It shouldn't return a null list", returnedProjectEntityList);
        assertEquals("It should return " + listSize + " elements in the list.", listSize, returnedProjectEntityList.size());

        verify(mockRepo, times(1)).findAll();
    }

    @Test
    public void should_throw_exception_when_pageable_to_find_all_is_null() throws ServiceException {
        exceptionRule.expect(ServiceException.class);
        exceptionRule.expectMessage("Can't find page with a null paginator.");

        doCallRealMethod()
                .when(sut)
                .findAll(any());

        sut.findAll(null);
    }

    @Test
    public void should_throw_exception_when_find_all_paginated_throw_exception() throws ServiceException {
        exceptionRule.expect(ServiceException.class);
        exceptionRule.expectMessage("Error when trying to read the entities page from database");

        final Pageable pageable = PageFactory.buildSimplePageable();
        final ProjectRepository mockRepo = mock(ProjectRepository.class);
        when(mockRepo.findAll(any(Pageable.class))).thenThrow(Exception.class);
        Whitebox.setInternalState(sut, "projectRepository", mockRepo);

        doCallRealMethod()
                .when(sut)
                .findAll(any());

        try {
            sut.findAll(pageable);
        } finally {
            verify(mockRepo, times(1)).findAll(pageable);
        }
    }

    @Test
    public void should_return_paginated_list_when_find_all_paginated_execute_correctly() throws ServiceException {
        final Page<ProjectEntity> projectEntitiesPage = PageFactory.buildSimplePage(ProjectEntityFactory::buildSimpleEntityWithoutId);
        final Pageable pageable = PageFactory.buildSimplePageable();
        final ProjectRepository mockRepo = mock(ProjectRepository.class);
        when(mockRepo.findAll(any(Pageable.class))).thenReturn(projectEntitiesPage);
        Whitebox.setInternalState(sut, "projectRepository", mockRepo);

        doCallRealMethod()
                .when(sut)
                .findAll(any(Pageable.class));

        final Page<ProjectEntity> returnedProjectEntitiesPage = sut.findAll(pageable);

        assertNotNull("The list returned shouldn't be null.", returnedProjectEntitiesPage);
        assertEquals("It should return only 5 elements.", 5, returnedProjectEntitiesPage.getContent().size());

        verify(mockRepo, times(1)).findAll(pageable);
    }

}
