package br.com.rk.controller;

import br.com.rk.entities.ProjectEntity;
import br.com.rk.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Rhuan Karlus
 * @since 04/03/2019
 */
public abstract class AbstractController<E extends ProjectEntity> {

    @Autowired
    private ProjectService<E> projectService;

    protected ProjectService<E> getService() {
        return this.projectService;
    }

}
