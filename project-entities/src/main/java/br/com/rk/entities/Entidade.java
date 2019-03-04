package br.com.rk.entities;

import java.io.Serializable;

/**
 * @author Rhuan Karlus
 * @since 03/03/19
 */
public interface Entidade<T extends Serializable> {

    T getId();

    void setId(T id);

}
