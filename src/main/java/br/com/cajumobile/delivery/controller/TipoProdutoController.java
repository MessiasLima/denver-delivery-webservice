package br.com.cajumobile.delivery.controller;

import br.com.cajumobile.delivery.exception.EntityNotFoundException;
import br.com.cajumobile.delivery.model.TipoProduto;
import br.com.cajumobile.delivery.service.TipoProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("produto/tipo")
@SuppressWarnings("unused")
public class TipoProdutoController {

    @Autowired
    private TipoProdutoService tipoProdutoService;

    @PostMapping
    public ResponseEntity<TipoProduto> saveOrUpdate(@RequestBody TipoProduto tipoProduto) {
        return ResponseEntity.ok(tipoProdutoService.saveOrUpdate(tipoProduto));
    }

    @DeleteMapping("/{idTipoProduto}")
    public ResponseEntity<?> delete(@PathVariable("idTipoProduto") Integer idTipoProduto) {
        try {
            tipoProdutoService.delete(idTipoProduto);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<TipoProduto>> list() {
        try {
            return ResponseEntity.ok(tipoProdutoService.findAll());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/estabelecimento/{idEstabelecimento}")
    public ResponseEntity<List<TipoProduto>> listarTipoProdutoPorEstabelecimento(@PathVariable("idEstabelecimento") Integer idEstabelecimento) {
        return ResponseEntity.ok(tipoProdutoService.listarPorEstabelecimento(idEstabelecimento));
    }

}
