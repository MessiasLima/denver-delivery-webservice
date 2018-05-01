package br.com.cajumobile.delivery.service;

import br.com.cajumobile.delivery.model.EstabelecimentoFormaPagamento;
import br.com.cajumobile.delivery.model.FormaPagamento;
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
        List<EstabelecimentoFormaPagamento> estabelecimentoFormaPagamentosSalvos = formaPagamentoRespository.listarEstabelecimentoFormaPagamentoPorEstabelecimento(idEstabelecimento);
        salvarOsQueVieramNaLista(formaPagamentos, idEstabelecimento, estabelecimentoFormaPagamentosSalvos);
        deletarOsQueNaoVieraNaLista(formaPagamentos, idEstabelecimento, estabelecimentoFormaPagamentosSalvos);
    }

    private void deletarOsQueNaoVieraNaLista(List<FormaPagamento> formaPagamentos, Integer idEstabelecimento, List<EstabelecimentoFormaPagamento> estabelecimentoFormaPagamentosSalvos) {
        estabelecimentoFormaPagamentosSalvos.forEach(estabelecimentoFormaPagamentoSalvo ->{
            List<EstabelecimentoFormaPagamento> estabelecimentoFormaPagamentosNaoSalvos = formaPagamentoParaTabelaDeRelacaoLista(formaPagamentos, idEstabelecimento);
            if (!estabelecimentoFormaPagamentosNaoSalvos.contains(estabelecimentoFormaPagamentoSalvo)){
                formaPagamentoRespository.deletarEstabelecimentoFormaPagamento(idEstabelecimento, estabelecimentoFormaPagamentoSalvo.getIdFormaPagamento());
            }
        });
    }

    private List<EstabelecimentoFormaPagamento> formaPagamentoParaTabelaDeRelacaoLista(List<FormaPagamento> formaPagamentos, Integer idEstabelecimento) {
        List<EstabelecimentoFormaPagamento> retorno = new ArrayList<>();
        formaPagamentos.forEach(formaPagamento -> retorno.add(new EstabelecimentoFormaPagamento(formaPagamento.getId(),idEstabelecimento)));
        return retorno;
    }

    private void salvarOsQueVieramNaLista(List<FormaPagamento> formaPagamentos, Integer idEstabelecimento, List<EstabelecimentoFormaPagamento> estabelecimentoFormaPagamentosSalvos) {
        formaPagamentos.forEach(formaPagamento -> {
            EstabelecimentoFormaPagamento estabelecimentoFormaPagamentoNaoSalvo = formaPagamentoParaTabelaDeRelacao(formaPagamento, idEstabelecimento);
            if (!estabelecimentoFormaPagamentosSalvos.contains(estabelecimentoFormaPagamentoNaoSalvo)){
                formaPagamentoRespository.salvarEstabelecimentoFormaPagamento(estabelecimentoFormaPagamentoNaoSalvo.getIdEstabelecimento(),estabelecimentoFormaPagamentoNaoSalvo.getIdFormaPagamento());
            }
        });
    }

    private EstabelecimentoFormaPagamento formaPagamentoParaTabelaDeRelacao(FormaPagamento formaPagamento, Integer idEstabelecimento) {
        return new EstabelecimentoFormaPagamento(formaPagamento.getId(), idEstabelecimento);
    }


}
