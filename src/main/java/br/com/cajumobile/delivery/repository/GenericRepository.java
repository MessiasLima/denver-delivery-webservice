package br.com.cajumobile.delivery.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class GenericRepository<T> {
    @PersistenceContext
    protected EntityManager entityManager;

    public void save(T entity){
        entityManager.persist(entity);
    }


    public T saveOrUpdate(T entity){
        return entityManager.merge(entity);
    }


    public void delete(T entity){
        entityManager.remove(entity);
    }
}
