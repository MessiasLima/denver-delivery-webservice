package br.com.cajumobile.delivery.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class GenericRepository<T> {
    @PersistenceContext
    protected EntityManager entityManager;

    public void salvar(T entity){
        entityManager.persist(entity);
    }


    public T salvarOuAtualizar(T entity){
        return entityManager.merge(entity);
    }


    public void remover(T entity){
        entityManager.remove(entity);
    }
}
