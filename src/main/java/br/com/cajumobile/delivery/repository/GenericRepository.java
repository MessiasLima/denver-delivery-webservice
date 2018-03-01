package br.com.cajumobile.delivery.repository;

import br.com.cajumobile.delivery.exception.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

    public T saveOrUpdate(T entity) {
        return entityManager.merge(entity);
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
}
