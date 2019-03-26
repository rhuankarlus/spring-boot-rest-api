package br.com.rk.controller;

import br.com.rk.controller.dto.DTO;
import br.com.rk.controller.dto.ProjectResponse;
import br.com.rk.converters.Conversor;
import br.com.rk.entities.ProjectEntity;
import br.com.rk.services.exception.ServicoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Rhuan Karlus
 * @since 04/03/2019
 */
public abstract class AbstractCrudControle<D extends DTO, E extends ProjectEntity> extends AbstractControle<E> {

    @Autowired
    private Conversor<D, E> conversor;

    @GetMapping
    @ResponseBody
    public ResponseEntity<ProjectResponse> findAll(@PageableDefault final Pageable pageable) throws ServicoException {
        return ResponseEntity.ok(convertPage(getService().findAll(pageable)));
    }

    @ResponseBody
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProjectResponse> getById(@PathVariable("id") final Long id) throws ServicoException {
        return ResponseEntity.ok(ProjectResponse.of(conversor.toDTO(getService().findById(id))));
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<ProjectResponse> persist(@RequestBody final D dto) throws ServicoException {
        return ResponseEntity.ok(ProjectResponse.of(conversor.toDTO(getService().persist(conversor.toEntity(dto)))));
    }

    @ResponseBody
    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") final Long id) throws ServicoException {
        getService().delete(id);
        return ResponseEntity.ok().build();
    }

    protected ProjectResponse convertPage(final Page<E> page) {
        final List<D> dtos = page.getContent().stream().map(conversor::toDTO).collect(Collectors.toList());
        final ProjectResponse.Pagination pagination = new ProjectResponse.Pagination(
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getSort());

        return ProjectResponse.of(null, dtos, pagination);
    }

}
