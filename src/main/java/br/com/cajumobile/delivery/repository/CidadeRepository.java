package br.com.cajumobile.delivery.repository;

import br.com.cajumobile.delivery.model.Cidade;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CidadeRepository extends GenericRepository<Cidade> {

    public CidadeRepository() {
        super(Cidade.class);
    }

    public List<Cidade> listCidades() {
        return entityManager.createQuery("FROM Cidade c", Cidade.class).getResultList();
    }

    public void deleteById(Integer idCidade) {
        entityManager.createQuery("DELETE FROM Cidade c WHERE c.id = :idCidade")
                .setParameter("idCidade", idCidade)
                .executeUpdate();
    }

    public Cidade findById(Integer idCidade) {
        return entityManager.createQuery("FROM Cidade c WHERE c.id = :id", Cidade.class)
                .setParameter("id", idCidade)
                .getSingleResult();
    }
}
