package br.com.rk.services.unit;

import br.com.rk.entities.ProjectEntity;
import br.com.rk.repositories.ProjectRepository;
import br.com.rk.services.AbstractCrudService;
import br.com.rk.services.exception.ServiceException;
import br.com.rk.services.factory.PageFactory;
import br.com.rk.services.factory.ProjectEntityFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Rhuan Karlus
 * @since 04/04/19
 */
@SuppressWarnings("unchecked")
public class AbstractCrudServiceTest {

    private AbstractCrudServiceTestImpl sut;

    /**
     * This is the class used to test the abstraction.
     * With this we don't need to use reflection to override methods and fields implementations
     */
    private class AbstractCrudServiceTestImpl extends AbstractCrudService {
        @Override
        protected ProjectRepository getRepository() {
            return null;
        }

        @Override
        protected Specification buildAllSpecifications(ProjectEntity entity) {
            return null;
        }

        @Override
        public ProjectEntity findById(long id) throws ServiceException {
            return super.findById(id);
        }

        @Override
        public ProjectEntity persist(ProjectEntity entidade) throws ServiceException {
            return super.persist(entidade);
        }

        @Override
        public void delete(long id) throws ServiceException {
            super.delete(id);
        }

        @Override
        public List findAll() throws ServiceException {
            return super.findAll();
        }

        @Override
        public Page findAll(Pageable pageable) throws ServiceException {
            return super.findAll(pageable);
        }

        @Override
        public Page findByExample(ProjectEntity entity, Pageable pageable) throws ServiceException {
            return super.findByExample(entity, pageable);
        }

        @Override
        public void validateBeforeFindExample(ProjectEntity entity, Pageable pageable) throws ServiceException {
            super.validateBeforeFindExample(entity, pageable);
        }
    }

    @BeforeEach
    public void setUp() {
        sut = mock(AbstractCrudServiceTestImpl.class);
    }

    @Test
    public void should_throw_exception_when_entity_not_found() throws ServiceException {
        final long id = 1L;

        final ProjectRepository mockRepo = mock(ProjectRepository.class);
        when(mockRepo.findById(id)).thenReturn(Optional.empty());
        when(sut.getRepository()).thenReturn(mockRepo);

        doCallRealMethod()
                .when(sut)
                .findById(id);

        final ServiceException expectedException =
                assertThrows(ServiceException.class, () -> sut.findById(id));

        assertEquals("Entity with ID 1 not found.", expectedException.getMessage());

        verify(mockRepo, times(1)).findById(id);
    }

    @Test
    public void should_return_correct_entity_by_id() throws ServiceException {
        final long id = 1L;
        final ProjectEntity projectEntity = ProjectEntityFactory.buildSimpleEntityWithId(id);

        final ProjectRepository mockRepo = mock(ProjectRepository.class);
        when(mockRepo.findById(id)).thenReturn(Optional.of(projectEntity));
        when(sut.getRepository()).thenReturn(mockRepo);

        doCallRealMethod()
                .when(sut)
                .findById(id);

        final ProjectEntity returnedProjectEntity = sut.findById(id);

        assertNotNull(returnedProjectEntity, "It shouldn't return null entity.");
        assertEquals(Long.valueOf(id), returnedProjectEntity.getId(), "It should return the entity with id " + id);

        verify(mockRepo, times(1)).findById(id);
    }

    @Test
    public void should_throw_exception_when_entity_to_persist_is_null() throws ServiceException {
        doCallRealMethod()
                .when(sut)
                .persist(any());

        final ServiceException expectedException =
                assertThrows(ServiceException.class, () -> sut.persist(null));

        assertEquals("The entity shouldn't be null.", expectedException.getMessage());
    }

    @Test
    public void should_throw_exception_when_persist_entity_throws_exception() throws ServiceException {
        final ProjectEntity projectEntity = ProjectEntityFactory.buildSimpleEntityWithId(1L);
        final ProjectRepository mockRepo = mock(ProjectRepository.class);
        doThrow(RuntimeException.class).when(mockRepo).save(projectEntity);
        when(sut.getRepository()).thenReturn(mockRepo);

        doCallRealMethod()
                .when(sut)
                .persist(projectEntity);

        final ServiceException expectedException =
                assertThrows(ServiceException.class, () -> sut.persist(projectEntity));

        assertEquals("Error trying to persist entity", expectedException.getMessage());

        verify(mockRepo, times(1)).save(projectEntity);
    }

    @Test
    public void should_return_correct_entity_when_persist() throws ServiceException {
        final Long id = 1L;

        final ProjectEntity projectEntity = ProjectEntityFactory.buildSimpleEntityWithoutId();
        final ProjectEntity projectEntityWithId = ProjectEntityFactory.buildSimpleEntityWithId(id);

        final ProjectRepository mockRepo = mock(ProjectRepository.class);
        when(mockRepo.save(projectEntity)).thenReturn(projectEntityWithId);
        when(sut.getRepository()).thenReturn(mockRepo);

        doCallRealMethod()
                .when(sut)
                .persist(projectEntity);

        final ProjectEntity returnedProjectEntity = sut.persist(projectEntity);

        assertNotNull(returnedProjectEntity, "It shouldn't return a null entity.");
        assertEquals(id, returnedProjectEntity.getId(), "It should return the entity with id " + id);

        verify(mockRepo, times(1)).save(projectEntity);
    }

    @Test
    public void should_throw_exception_when_delete_fail() throws ServiceException {
        final long id = 1L;
        final ProjectRepository mockRepo = mock(ProjectRepository.class);
        doThrow(RuntimeException.class).when(mockRepo).deleteById(isA(Long.class));
        when(sut.getRepository()).thenReturn(mockRepo);

        doCallRealMethod()
                .when(sut)
                .delete(id);

        final ServiceException expectedException =
                assertThrows(ServiceException.class, () -> sut.delete(id));

        assertEquals("Error trying to delete the entity", expectedException.getMessage());

        verify(mockRepo, times(1)).deleteById(id);
    }

    @Test
    public void should_delete_entity_correctly() throws ServiceException {
        final long id = 1L;

        final ProjectRepository mockRepo = mock(ProjectRepository.class);
        doNothing().when(mockRepo).deleteById(id);
        when(sut.getRepository()).thenReturn(mockRepo);

        doCallRealMethod()
                .when(sut)
                .delete(id);

        sut.delete(id);

        verify(mockRepo, times(1)).deleteById(id);
    }

    @Test
    public void should_throw_exception_when_find_all_fail() throws ServiceException {
        final ProjectRepository mockRepo = mock(ProjectRepository.class);
        when(mockRepo.findAll()).thenThrow(RuntimeException.class);
        when(sut.getRepository()).thenReturn(mockRepo);

        doCallRealMethod()
                .when(sut)
                .findAll();

        final ServiceException expectedException =
                assertThrows(ServiceException.class, () -> sut.findAll());

        assertEquals("Error when trying to read the entities list from database", expectedException.getMessage());

        verify(mockRepo, times(1)).findAll();
    }

    @Test
    public void should_return_result_list_when_find_all_execute_correctly() throws ServiceException {
        final int listSize = 5;
        final List<ProjectEntity> projectEntityList = ProjectEntityFactory.buildSimpleListWithoutId(listSize);

        final ProjectRepository mockRepo = mock(ProjectRepository.class);
        when(mockRepo.findAll()).thenReturn(projectEntityList);
        when(sut.getRepository()).thenReturn(mockRepo);

        doCallRealMethod()
                .when(sut)
                .findAll();

        final List<ProjectEntity> returnedProjectEntityList = sut.findAll();

        assertNotNull(returnedProjectEntityList, "It shouldn't return a null list");
        assertEquals(listSize, returnedProjectEntityList.size(), "It should return " + listSize + " elements in the list.");

        verify(mockRepo, times(1)).findAll();
    }

    @Test
    public void should_throw_exception_when_pageable_to_find_all_is_null() throws ServiceException {
        doCallRealMethod()
                .when(sut)
                .findAll(any());

        final ServiceException expectedException =
                assertThrows(ServiceException.class, () -> sut.findAll(null));

        assertEquals("Can't find page with a null paginator.", expectedException.getMessage());
    }

    @Test
    public void should_throw_exception_when_find_all_paginated_throw_exception() throws ServiceException {
        final Pageable pageable = PageFactory.buildSimplePageable();
        final ProjectRepository mockRepo = mock(ProjectRepository.class);
        doThrow(RuntimeException.class).when(mockRepo).findAll(any(Pageable.class));
        when(sut.getRepository()).thenReturn(mockRepo);

        doCallRealMethod()
                .when(sut)
                .findAll(any());

        final ServiceException expectedException =
                assertThrows(ServiceException.class, () -> sut.findAll(pageable));

        assertEquals("Error when trying to read the entities page from database", expectedException.getMessage());

        verify(mockRepo, times(1)).findAll(pageable);
    }

    @Test
    public void should_return_paginated_list_when_find_all_paginated_execute_correctly() throws ServiceException {
        final Page<ProjectEntity> projectEntitiesPage = PageFactory.buildSimplePage(ProjectEntityFactory::buildSimpleEntityWithoutId);
        final Pageable pageable = PageFactory.buildSimplePageable();
        final ProjectRepository mockRepo = mock(ProjectRepository.class);
        when(mockRepo.findAll(any(Pageable.class))).thenReturn(projectEntitiesPage);
        when(sut.getRepository()).thenReturn(mockRepo);

        doCallRealMethod()
                .when(sut)
                .findAll(any(Pageable.class));

        final Page<ProjectEntity> returnedProjectEntitiesPage = sut.findAll(pageable);

        assertNotNull(returnedProjectEntitiesPage, "The list returned shouldn't be null.");
        assertEquals(5, returnedProjectEntitiesPage.getContent().size(), "It should return only 5 elements.");

        verify(mockRepo, times(1)).findAll(pageable);
    }

    @Test
    public void should_throw_exception_when_entity_is_null_before_find_by_example() throws ServiceException {
        doCallRealMethod()
                .when(sut)
                .findByExample(any(), any());

        doCallRealMethod()
                .when(sut)
                .validateBeforeFindExample(any(), any());

        final ServiceException expectedException =
                assertThrows(ServiceException.class, () -> sut.findByExample(null, null));

        assertEquals("The entity object can't be null.", expectedException.getLocalizedMessage());
    }

    @Test
    public void should_throw_exception_when_pageable_is_null_before_find_by_example() throws ServiceException {
        doCallRealMethod()
                .when(sut)
                .findByExample(any(), any());

        doCallRealMethod()
                .when(sut)
                .validateBeforeFindExample(any(), any());

        final ServiceException expectedException =
                assertThrows(ServiceException.class, () -> sut.findByExample(ProjectEntityFactory.buildSimpleEntityWithoutId(), null));

        assertEquals("The pageable object can't be null.", expectedException.getMessage());
    }

}