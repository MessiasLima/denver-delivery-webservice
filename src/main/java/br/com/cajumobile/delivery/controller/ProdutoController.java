package br.com.cajumobile.delivery.controller;

import br.com.cajumobile.delivery.service.TipoProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private TipoProdutoService tipoProdutoService;

    @RequestMapping("/tipo")
    public ResponseEntity<?> listarTodos() {
        try {
            return new ResponseEntity<>(tipoProdutoService.listarTodos(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
