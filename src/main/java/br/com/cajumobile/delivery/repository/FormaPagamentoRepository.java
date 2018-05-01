package br.com.cajumobile.delivery.repository;

import br.com.cajumobile.delivery.model.EstabelecimentoFormaPagamento;
import br.com.cajumobile.delivery.model.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Integer> {

    @Query("FROM EstabelecimentoFormaPagamento efp WHERE efp.idEstabelecimento = :idEstabelecimento")
    List<EstabelecimentoFormaPagamento> listarEstabelecimentoFormaPagamentoPorEstabelecimento(@Param("idEstabelecimento") Integer idEstabelecimento);

    @Query("FROM FormaPagamento fp WHERE fp.id in(:ids)")
    List<FormaPagamento> listarFormaPagamentoPorIds(@Param("ids") List<Integer> integers);
}
