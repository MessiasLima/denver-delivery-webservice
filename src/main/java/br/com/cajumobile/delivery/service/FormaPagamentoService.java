package br.com.cajumobile.delivery.service;

import br.com.cajumobile.delivery.model.EstabelecimentoFormaPagamento;
import br.com.cajumobile.delivery.model.FormaPagamento;
import br.com.cajumobile.delivery.repository.EstabelecimentoFormaPagamentoRepository;
import br.com.cajumobile.delivery.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class FormaPagamentoService {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRespository;


    @Autowired
    private EstabelecimentoFormaPagamentoRepository estabelecimentoFormaPagamentoRepository;

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

    @Transactional
    public FormaPagamento salvar(FormaPagamento formaPagamento) {
        return formaPagamentoRespository.save(formaPagamento);
    }

    @Transactional
    public void salvarPorEstabelecimento(List<FormaPagamento> formaPagamentos, Integer idEstabelecimento) {
        estabelecimentoFormaPagamentoRepository.saveOrUpdateList(montarEstabelecimentosFormaPagamento(formaPagamentos, idEstabelecimento));
    }

    private List<EstabelecimentoFormaPagamento> montarEstabelecimentosFormaPagamento(List<FormaPagamento> formaPagamentos, Integer idEstabelecimento) {
        List<EstabelecimentoFormaPagamento> estabelecimentoFormaPagamentos = new ArrayList<>();
        formaPagamentos.forEach(formaPagamento -> {
            estabelecimentoFormaPagamentos.add(new EstabelecimentoFormaPagamento(formaPagamento.getId(), idEstabelecimento));
        });
        return estabelecimentoFormaPagamentos;
    }

}
