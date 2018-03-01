package br.com.cajumobile.delivery.controller;

import br.com.cajumobile.delivery.configuration.SecurityConfiguration;
import br.com.cajumobile.delivery.exception.EntityNotFoundException;
import br.com.cajumobile.delivery.exception.InvalidFileException;
import br.com.cajumobile.delivery.model.Estabelecimento;
import br.com.cajumobile.delivery.model.Usuario;
import br.com.cajumobile.delivery.service.EstabelecimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/estabelecimento")
public class EstabelecimentoController {

    @Autowired
    private EstabelecimentoService estabelecimentoService;

    @GetMapping
    public ResponseEntity<?> listEstabelecimentos(@RequestAttribute(SecurityConfiguration.AUTHETICATED_USER) Usuario usuario) {
        List<Estabelecimento> estabelecimentos = estabelecimentoService.listByUsuario(usuario);
        return new ResponseEntity<>(estabelecimentos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveEstabelecimento(@RequestBody Estabelecimento estabelecimento) {
        Estabelecimento saved = estabelecimentoService.save(estabelecimento);
        if (estabelecimento.getId() == null) {
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(saved, HttpStatus.OK);
        }
    }

    @PostMapping("/image")
    public ResponseEntity<?> saveImageEstacelecimento(@RequestParam("file") MultipartFile file, @RequestParam("idEstabelecimento") Integer idEstabelecimento) {
        try {
            estabelecimentoService.updateImage(file, idEstabelecimento);
            return ResponseEntity.ok().build();
        } catch (InvalidFileException | EntityNotFoundException e) {
            return new ResponseEntity<>(e.getCauseDTO(), HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
