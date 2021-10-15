package school.cesar.dao;

import school.cesar.model.AbstractNextEntity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;

public abstract class AbstractDAO<EntityType extends AbstractNextEntity> implements DAO<EntityType> {

    private final Class<EntityType> entityTypeClass;

    private final EntityManager entityManager;

    protected AbstractDAO(Class<EntityType> entityTypeClass) {
        super();
        this.entityTypeClass = entityTypeClass;

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("school.cesar.next.PU");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public EntityType find(EntityType entity) {
        return this.entityManager.find(this.entityTypeClass, entity.getId());
    }

    @Override
    public Collection<EntityType> findAll() {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<EntityType> criteriaQuery = criteriaBuilder.createQuery(this.entityTypeClass);
        Root<EntityType> root = criteriaQuery.from(this.entityTypeClass);
        TypedQuery<EntityType> typedQuery = this.entityManager.createQuery(criteriaQuery);

        return typedQuery.getResultList();
    }

    @Override
    public EntityType save(EntityType entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.flush();
        this.entityManager.getTransaction().commit();

        return this.find(entity);
    }

    @Override
    public EntityType update(EntityType entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(entity);
        this.entityManager.flush();
        this.entityManager.getTransaction().commit();

        return this.find(entity);
    }

    @Override
    public void delete(EntityType entity) {
        entity = this.find(entity);

        if(entity!=null && entity.getId()!=0) {
            this.entityManager.getTransaction().begin();
            entity = this.update(entity);
            this.entityManager.remove(entity);
            this.entityManager.flush();
            this.entityManager.getTransaction().commit();
        }
    }
}
