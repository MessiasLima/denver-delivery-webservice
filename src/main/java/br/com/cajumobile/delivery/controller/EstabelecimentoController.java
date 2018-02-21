package br.com.cajumobile.delivery.controller;

import br.com.cajumobile.delivery.configuration.SecurityConfiguration;
import br.com.cajumobile.delivery.model.Estabelecimento;
import br.com.cajumobile.delivery.model.Usuario;
import br.com.cajumobile.delivery.service.EstabelecimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estabelecimento")
public class EstabelecimentoController {

    @Autowired
    private EstabelecimentoService estabelecimentoService;

    @RequestMapping
    public ResponseEntity<?> listEstabelecimentos(@RequestAttribute(SecurityConfiguration.AUTHETICATED_USER)Usuario usuario){
        List<Estabelecimento> estabelecimentos = estabelecimentoService.listByUsuario(usuario);
        return new ResponseEntity<>(estabelecimentos, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> saveEstabelecimento(@RequestBody Estabelecimento estabelecimento){
        Estabelecimento saved = estabelecimentoService.save(estabelecimento);
        if (estabelecimento.getId() == null){
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(saved, HttpStatus.OK);
        }
    }
}
