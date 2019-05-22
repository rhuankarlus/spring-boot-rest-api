package br.com.rk.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Rhuan Karlus
 * @since 03/03/19
 */
@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEntity implements ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
