package br.com.rk.controller;

import br.com.rk.controller.dto.DTO;
import br.com.rk.controller.dto.ProjectResponse;
import br.com.rk.converters.Conversor;
import br.com.rk.entities.Entidade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Rhuan Karlus
 * @since 04/03/2019
 */
public abstract class AbstractCrudControle<D extends DTO, E extends Entidade> extends AbstractControle<E> {

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProjectResponse> getById(@PathVariable("id") Long id) {
        return null;
    }

    @PostMapping
    public ResponseEntity<ProjectResponse> salvar(@RequestBody D dto) {
        return null;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ProjectResponse> deletar(@PathVariable("id") Long id) {
        return null;
    }

    /**
     * @return O conversor do DTO para a entidade aceitaos por esse controlador
     */
    protected abstract Conversor<D, E> getConversor();

}
