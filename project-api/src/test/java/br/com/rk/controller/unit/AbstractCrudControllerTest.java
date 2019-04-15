package br.com.rk.controller.unit;

import br.com.rk.controller.AbstractControllerTest;
import br.com.rk.controller.dto.DTO;
import br.com.rk.converters.Conversor;
import br.com.rk.entities.ProjectEntity;
import br.com.rk.services.ProjectCrudService;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * @author Rhuan Karlus
 * @since 09/04/2019
 */
public abstract class AbstractCrudControllerTest<E extends ProjectEntity, D extends DTO> extends AbstractControllerTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @MockBean
    protected ProjectCrudService<E> mockService;
    @MockBean
    protected Conversor<D, E> mockConversor;

}
