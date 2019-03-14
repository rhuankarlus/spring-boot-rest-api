package br.com.rk.controller;

import br.com.rk.controller.dto.DTO;
import br.com.rk.controller.dto.ProjectResponse;
import br.com.rk.entities.Entidade;
import br.com.rk.services.exception.ServicoException;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;

/**
 * @author Rhuan Karlus
 * @since 04/03/2019
 */
public abstract class AbstractCrudControle<D extends DTO, E extends Entidade> extends AbstractControle<E> implements HasConversor<D, E> {

    @GetMapping
    @ResponseBody
    public ResponseEntity<ProjectResponse> findAll(@PageableDefault Pageable pageable) throws ServicoException {
//        return ResponseEntity.ok(ProjectResponse.of(getConversor().toDTO(getServico().findById(id))));
    }

    @ResponseBody
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProjectResponse> getById(@PathVariable("id") Long id) throws ServicoException {
        return ResponseEntity.ok(ProjectResponse.of(getConversor().toDTO(getServico().findById(id))));
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<ProjectResponse> persist(@RequestBody D dto) throws ServicoException {
        return ResponseEntity.ok(ProjectResponse.of(
                getConversor().toDTO(getServico().persist(getConversor().toEntity(dto)))));
    }

    @ResponseBody
    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) throws ServicoException {
        getServico().delete(id);
        return ResponseEntity.ok().build();
    }

}
