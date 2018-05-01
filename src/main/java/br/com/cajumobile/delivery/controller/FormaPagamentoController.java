package br.com.cajumobile.delivery.controller;

import br.com.cajumobile.delivery.model.EstabelecimentoFormaPagamento;
import br.com.cajumobile.delivery.model.FormaPagamento;
import br.com.cajumobile.delivery.service.FormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("unused")
@RestController
@RequestMapping("forma-pagamento")
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @GetMapping
    public ResponseEntity<List<FormaPagamento>> listarTodos() {
        List<FormaPagamento> formaPagamentos = formaPagamentoService.listarTodos();
        if (formaPagamentos == null || formaPagamentos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(formaPagamentos);
        }
    }

    @GetMapping("estabelecimento/{idEstabelecimento}")
    public ResponseEntity<List<FormaPagamento>> listarPorEstabelecimento(
            @PathVariable("idEstabelecimento") Integer idEstabelecimento
    ) {
        List<FormaPagamento> formaPagamentos = formaPagamentoService.listarPorEstabelecimento(idEstabelecimento);
        if (formaPagamentos == null || formaPagamentos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(formaPagamentos);
        }
    }

    @PostMapping
    public ResponseEntity<FormaPagamento> salvarFormaPagamento(@RequestBody FormaPagamento formaPagamento) {
        return ResponseEntity.ok(formaPagamentoService.salvar(formaPagamento));
    }

    @PostMapping("estabelecimento/{idEstabelecimento}")
    public ResponseEntity<List<EstabelecimentoFormaPagamento>> salvarFormaPagamentoPorEstabelecimento(
            @RequestBody List<FormaPagamento> formaPagamentos,
            @PathVariable("idEstabelecimento") Integer idEstabelecimento
    ) {
        formaPagamentoService.salvarPorEstabelecimento(formaPagamentos, idEstabelecimento);
        return ResponseEntity.ok().build();
    }
}
