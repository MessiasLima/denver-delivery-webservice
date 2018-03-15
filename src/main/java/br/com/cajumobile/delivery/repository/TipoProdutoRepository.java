package br.com.cajumobile.delivery.repository;

import br.com.cajumobile.delivery.model.TipoProduto;
import org.springframework.stereotype.Repository;

@Repository
public class TipoProdutoRepository extends GenericRepository<TipoProduto> {
    protected TipoProdutoRepository() {
        super(TipoProduto.class);
    }
}
