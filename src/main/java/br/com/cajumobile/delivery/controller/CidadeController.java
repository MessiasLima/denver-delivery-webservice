package br.com.cajumobile.delivery.controller;

import br.com.cajumobile.delivery.configuration.SecurityConfiguration;
import br.com.cajumobile.delivery.exception.NoPermissionException;
import br.com.cajumobile.delivery.model.Cidade;
import br.com.cajumobile.delivery.model.Usuario;
import br.com.cajumobile.delivery.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidade")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> saveCidade(
            @RequestAttribute(SecurityConfiguration.AUTHETICATED_USER) Usuario usuario,
            @RequestParam("nome") String nomeCidade) {
        try {
            Cidade cidade = cidadeService.saveCidade(usuario, nomeCidade);
            return new ResponseEntity<>(cidade, HttpStatus.OK);
        } catch (NoPermissionException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping
    public ResponseEntity<?> listCidades() {
        List<Cidade> cidades = cidadeService.listCidades();
        return new ResponseEntity<>(cidades, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updateCidade(@RequestBody Cidade cidade) {
        return new ResponseEntity<Cidade>(cidadeService.updateCidade(cidade), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCidade(@RequestParam("id") Integer idCidade) {
        cidadeService.deleteCidade(idCidade);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
