package br.com.rk.controller;

import br.com.rk.controller.dto.DTO;
import br.com.rk.controller.dto.ProjectResponse;
import br.com.rk.converters.Conversor;
import br.com.rk.converters.ConverterException;
import br.com.rk.entities.ProjectEntity;
import br.com.rk.services.ProjectCrudService;
import br.com.rk.services.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Rhuan Karlus
 * @since 04/03/2019
 */
public abstract class AbstractCrudController<D extends DTO, E extends ProjectEntity> {

    @Autowired
    private ProjectCrudService<E> projectCrudService;
    @Autowired
    private Conversor<D, E> conversor;

    @GetMapping
    @ResponseBody
    public ResponseEntity<ProjectResponse> findAll(@PageableDefault final Pageable pageable) throws ServiceException {
        return ResponseEntity.ok(convertPage(getService().findAll(pageable)));
    }

    @PostMapping("/filter")
    @ResponseBody
    public ResponseEntity<ProjectResponse> findAll(@RequestBody D dto, @PageableDefault final Pageable pageable) throws ServiceException {
        return ResponseEntity.ok(convertPage(getService().findByExample(conversor.toEntity(dto), pageable)));
    }

    @ResponseBody
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProjectResponse> getById(@PathVariable("id") final Long id) throws ServiceException {
        return ResponseEntity.ok(ProjectResponse.of(conversor.toDTO(getService().findById(id))));
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<ProjectResponse> persist(@RequestBody final D dto) throws ServiceException {
        return ResponseEntity.ok(ProjectResponse.of(conversor.toDTO(getService().persist(conversor.toEntity(dto)))));
    }

    @ResponseBody
    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") final Long id) throws ServiceException {
        getService().delete(id);
        return ResponseEntity.ok().build();
    }

    protected ProjectResponse convertPage(final Page<E> page) throws ConverterException {
        final List<D> dtos = new ArrayList<>();
        for (final E entity : page.getContent()) {
            dtos.add(conversor.toDTO(entity));
        }

        final ProjectResponse.Pagination pagination = new ProjectResponse.Pagination(
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getSort());

        return ProjectResponse.of(null, dtos, pagination);
    }

    protected ProjectCrudService<E> getService() {
        return this.projectCrudService;
    }

}
