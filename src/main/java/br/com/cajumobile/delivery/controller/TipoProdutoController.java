package br.com.cajumobile.delivery.controller;

import br.com.cajumobile.delivery.exception.EntityNotFoundException;
import br.com.cajumobile.delivery.model.TipoProduto;
import br.com.cajumobile.delivery.service.TipoProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("produto/tipo")
public class TipoProdutoController {

    @Autowired
    private TipoProdutoService tipoProdutoService;

    @PostMapping
    public ResponseEntity<TipoProduto> saveOrUpdate(@RequestBody TipoProduto tipoProduto) {
        return ResponseEntity.ok(tipoProdutoService.saveOrUpdate(tipoProduto));
    }

    @GetMapping
    public ResponseEntity<List<TipoProduto>> list() {
        try {
            return ResponseEntity.ok(tipoProdutoService.findAll());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.noContent().build();
        }
    }

}
