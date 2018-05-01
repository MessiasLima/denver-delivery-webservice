package br.com.cajumobile.delivery.repository;

import br.com.cajumobile.delivery.exception.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class GenericRepository<T> {
    private Class<T> clazz;

    @PersistenceContext
    protected EntityManager entityManager;

    protected GenericRepository() {
    }

    protected GenericRepository(Class<T> clazz) {
        this.clazz = clazz;
    }

    public void save(T entity) {
        entityManager.persist(entity);
    }

    public void save(List<T> entityList) {
        entityList.forEach(this::save);
    }

    public T saveOrUpdate(T entity) {
        return entityManager.merge(entity);
    }

    public void saveOrUpdateList(List<T> entityList) {
        entityList.forEach(this::saveOrUpdate);
    }

    public void delete(T entity) {
        entityManager.remove(entity);
    }

    public T findById(Integer id) throws EntityNotFoundException {
        T entity = entityManager.find(clazz, id);
        if (entity == null) {
            throw new EntityNotFoundException(clazz, id);
        }
        return entity;
    }

    public List<T> findAll() throws EntityNotFoundException {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = builder.createQuery(clazz);
        Root<T> root = cq.from(clazz);
        cq.select(root);
        List<T> entities = this.entityManager.createQuery(cq).getResultList();
        if (entities == null || entities.isEmpty()) {
            throw new EntityNotFoundException(clazz, 0);
        }
        return entities;
    }
}
