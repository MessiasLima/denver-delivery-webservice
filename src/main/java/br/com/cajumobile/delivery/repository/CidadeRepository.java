package br.com.cajumobile.delivery.repository;

import br.com.cajumobile.delivery.model.Cidade;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CidadeRepository extends GenericRepository<Cidade> {
    public List<Cidade> listCidades() {
        return entityManager.createQuery("FROM Cidade c", Cidade.class).getResultList();
    }
}
