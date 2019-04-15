package br.com.rk.util.builders;

import br.com.rk.entities.ProjectEntity;

/**
 * @author Rhuan Karlus
 * @since 11/04/2019
 */
public abstract class AbstractEntityBuilder<E extends ProjectEntity> {

    public AbstractEntityBuilder id(Long id) {
        getEntity().setId(id);
        return this;
    }

    public E build() {
        return getEntity();
    }

    protected abstract E getEntity();

}
