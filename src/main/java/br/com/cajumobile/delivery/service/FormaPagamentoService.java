package br.com.cajumobile.delivery.service;

import br.com.cajumobile.delivery.model.FormaPagamento;
import br.com.cajumobile.delivery.repository.FormaPagamentoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormaPagamentoService {

    @Autowired
    private FormaPagamentoRespository formaPagamentoRespository;

    public List<FormaPagamento> listarTodos() {
        return formaPagamentoRespository.findAll();
    }
}
