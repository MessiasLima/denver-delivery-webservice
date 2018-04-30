package br.com.cajumobile.delivery.controller;

import br.com.cajumobile.delivery.model.FormaPagamento;
import br.com.cajumobile.delivery.service.FormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SuppressWarnings("unused")
@RestController
@RequestMapping("forma-pagamento")
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        List<FormaPagamento> formaPagamentos = formaPagamentoService.listarTodos();
        if (formaPagamentos == null || formaPagamentos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(formaPagamentos);
        }
    }
}
