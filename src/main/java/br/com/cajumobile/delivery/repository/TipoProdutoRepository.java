package br.com.cajumobile.delivery.repository;

import br.com.cajumobile.delivery.model.TipoProduto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TipoProdutoRepository extends GenericRepository<TipoProduto> {

    public List<TipoProduto> listarTodos() {
        return entityManager.createQuery("FROM TipoProduto", TipoProduto.class).getResultList();
    }
}
