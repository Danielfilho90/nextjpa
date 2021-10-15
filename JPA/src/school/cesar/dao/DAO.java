package school.cesar.dao;

import school.cesar.model.NextEntity;
import java.util.Collection;

public interface DAO<EntityType extends NextEntity> {
    EntityType find(EntityType entity);

    Collection<EntityType> findAll();

    EntityType save(EntityType entity);

    EntityType update(EntityType entity);

    void delete(EntityType entity);
}
