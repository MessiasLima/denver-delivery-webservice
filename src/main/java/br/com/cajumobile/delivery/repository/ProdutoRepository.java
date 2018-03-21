package br.com.cajumobile.delivery.repository;

import br.com.cajumobile.delivery.model.Produto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProdutoRepository extends GenericRepository<Produto> {
    public ProdutoRepository() {
        super(Produto.class);
    }

    public List<Produto> findByEstabelecimento(Integer idEstabelecimento) {
        return entityManager.createQuery("FROM Produto p WHERE p.idEstabelecimento = :idEstabelecimento", Produto.class)
                .setParameter("idEstabelecimento", idEstabelecimento)
                .getResultList();
    }
}
