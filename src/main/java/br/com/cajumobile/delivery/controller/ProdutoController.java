package br.com.cajumobile.delivery.controller;

import br.com.cajumobile.delivery.configuration.SecurityConfiguration;
import br.com.cajumobile.delivery.exception.EntityNotFoundException;
import br.com.cajumobile.delivery.exception.NoPermissionException;
import br.com.cajumobile.delivery.model.Produto;
import br.com.cajumobile.delivery.model.Usuario;
import br.com.cajumobile.delivery.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<Produto>> listAll(@RequestAttribute(SecurityConfiguration.AUTHETICATED_USER) Usuario usuario) {
        try {
            return ResponseEntity.ok(produtoService.findAll(usuario));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (NoPermissionException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/estabelecimento/{idEstabelecimento}")
    public ResponseEntity<List<Produto>> listByEstabelecimento(
            @RequestAttribute(SecurityConfiguration.AUTHETICATED_USER) Usuario usuario,
            @PathVariable Integer idEstabelecimento
    ) {
        try {
            return ResponseEntity.ok(produtoService.findByEstabelecimento(usuario, idEstabelecimento));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (NoPermissionException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping
    public ResponseEntity<Produto> create(
            @RequestAttribute(SecurityConfiguration.AUTHETICATED_USER) Usuario usuario,
            @RequestBody Produto produto
    ) {
        try {
            return ResponseEntity.ok(produtoService.save(usuario, produto));
        } catch (NoPermissionException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
