package br.com.rk.util;

import br.com.rk.entities.ProjectEntity;

import java.util.ArrayList;
import java.util.List;

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

    public static List<ProjectEntity> buildSimpleListWithoutId(final int size) {
        final List<ProjectEntity> projectEntityList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            projectEntityList.add(buildSimpleEntityWithoutId());
        }

        return projectEntityList;
    }
}
