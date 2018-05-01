package br.com.cajumobile.delivery.service;

import br.com.cajumobile.delivery.model.EstabelecimentoFormaPagamento;
import br.com.cajumobile.delivery.model.FormaPagamento;
import br.com.cajumobile.delivery.repository.FormaPagamentoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FormaPagamentoService {

    @Autowired
    private FormaPagamentoRespository formaPagamentoRespository;

    public List<FormaPagamento> listarTodos() {
        return formaPagamentoRespository.findAll();
    }

    public List<FormaPagamento> listarPorEstabelecimento(Integer idEstabelecimento) {
        List<EstabelecimentoFormaPagamento> estabelecimentFormaPagamentos = formaPagamentoRespository.listarEstabelecimentoFormaPagamentoPorEstabelecimento(idEstabelecimento);
        return formaPagamentoRespository.listarFormaPagamentoPorIds(extrairIdsFormaPagamento(estabelecimentFormaPagamentos));
    }

    private List<Integer> extrairIdsFormaPagamento(List<EstabelecimentoFormaPagamento> estabelecimentoFormaPagamentos) {
        List<Integer> idsFormasPagamento = new ArrayList<>();
        estabelecimentoFormaPagamentos.forEach(estabelecimentoFormaPagamento -> idsFormasPagamento.add(estabelecimentoFormaPagamento.getIdFormaPagamento()));
        return idsFormasPagamento;
    }
}
