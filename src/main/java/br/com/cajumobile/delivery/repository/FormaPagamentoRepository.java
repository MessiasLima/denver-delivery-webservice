package br.com.cajumobile.delivery.repository;

import br.com.cajumobile.delivery.model.EstabelecimentoFormaPagamento;
import br.com.cajumobile.delivery.model.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Integer> {

    @Query("FROM EstabelecimentoFormaPagamento efp WHERE efp.idEstabelecimento = :idEstabelecimento")
    List<EstabelecimentoFormaPagamento> listarEstabelecimentoFormaPagamentoPorEstabelecimento(@Param("idEstabelecimento") Integer idEstabelecimento);

    @Query("FROM FormaPagamento fp WHERE fp.id in(:ids)")
    List<FormaPagamento> listarFormaPagamentoPorIds(@Param("ids") List<Integer> integers);

    @Modifying
    @Query(value = "INSERT INTO estabelecimento_forma_pagamento(id_estabelecimento, id_forma_pagamento) VALUES(:idEstabelecimento,:idFormaPagamento)", nativeQuery = true)
    Integer salvarEstabelecimentoFormaPagamento(@Param("idEstabelecimento") Integer idEstabelecimento, @Param("idFormaPagamento") Integer idFormaPagameto);

    @Modifying
    @Query(value = "DELETE FROM estabelecimento_forma_pagamento WHERE id_estabelecimento = :idEstabelecimento AND id_forma_pagamento = :idFormaPagamento", nativeQuery = true)
    Integer deletarEstabelecimentoFormaPagamento(@Param("idEstabelecimento") Integer idEstabelecimento, @Param("idFormaPagamento") Integer idFormaPagamento);
}
