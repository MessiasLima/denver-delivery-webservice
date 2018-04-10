package br.com.cajumobile.delivery.controller;

import br.com.cajumobile.delivery.configuration.SecurityConfiguration;
import br.com.cajumobile.delivery.exception.EntityNotFoundException;
import br.com.cajumobile.delivery.exception.InvalidFileException;
import br.com.cajumobile.delivery.exception.NoPermissionException;
import br.com.cajumobile.delivery.model.Produto;
import br.com.cajumobile.delivery.model.Usuario;
import br.com.cajumobile.delivery.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/produto")
@SuppressWarnings("unused")
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

    @PostMapping("/image")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("idProduto") Integer idProduto){
        try {
            produtoService.updateImage(file, idProduto);
            return ResponseEntity.ok().build();
        } catch (InvalidFileException | IOException e) {
            return ResponseEntity.badRequest().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> findById(@PathVariable("id") Integer id){
        try {
            return ResponseEntity.ok(produtoService.findById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
