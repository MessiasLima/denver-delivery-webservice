package br.com.cajumobile.delivery.service;

import br.com.cajumobile.delivery.model.TipoProduto;
import br.com.cajumobile.delivery.repository.TipoProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoProdutoService {

    @Autowired
    private TipoProdutoRepository tipoProdutoRepository;

    public List<TipoProduto> listarTodos() {
        return tipoProdutoRepository.listarTodos();
    }
}
