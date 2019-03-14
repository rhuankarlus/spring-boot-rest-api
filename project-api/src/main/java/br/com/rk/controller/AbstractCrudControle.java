package br.com.rk.controller;

import br.com.rk.controller.dto.DTO;
import br.com.rk.controller.dto.ProjectResponse;
import br.com.rk.entities.Entidade;
import br.com.rk.services.exception.ServicoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Rhuan Karlus
 * @since 04/03/2019
 */
public abstract class AbstractCrudControle<D extends DTO, E extends Entidade> extends AbstractControle<E> implements HasConversor<D, E> {

    @ResponseBody
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProjectResponse> getById(@PathVariable("id") Long id) throws ServicoException {
        return ResponseEntity.ok(ProjectResponse.of(getConversor().toDTO(getServico().findById(id))));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<ProjectResponse> salvar(@RequestBody D dto) {
        return null;
    }

    @ResponseBody
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ProjectResponse> deletar(@PathVariable("id") Long id) {
        return null;
    }

}
