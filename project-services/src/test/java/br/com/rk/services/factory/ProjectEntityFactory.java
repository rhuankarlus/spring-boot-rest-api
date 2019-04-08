package br.com.rk.services.factory;

import br.com.rk.entities.ProjectEntity;

/**
 * @author Rhuan Karlus
 * @since 08/04/19
 */
public class ProjectEntityFactory {

    public static ProjectEntity buildSimpleEntityWithoutId() {
        return buildSimpleEntityWithId(null);
    }

    public static ProjectEntity buildSimpleEntityWithId(final Long id) {
        final ProjectEntity projectEntity = new ProjectEntity() {
            private Long id;

            @Override
            public Long getId() {
                return id;
            }

            @Override
            public void setId(Long id) {
                this.id = id;
            }
        };

        projectEntity.setId(id);
        return projectEntity;
    }

}
