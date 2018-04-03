package br.com.cajumobile.delivery.repository;

import br.com.cajumobile.delivery.model.TipoProduto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TipoProdutoRepository extends GenericRepository<TipoProduto> {
    protected TipoProdutoRepository() {
        super(TipoProduto.class);
    }

    public List<TipoProduto> listarPorEstabelecimento(Integer idEstabelecimento) {
        return entityManager.createQuery("FROM TipoProduto t WHERE t.idEstabelecimento = :id", TipoProduto.class)
                .setParameter("id", idEstabelecimento)
                .getResultList();
    }
}
