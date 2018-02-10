package br.com.cajumobile.delivery.controller;

import br.com.cajumobile.delivery.model.Usuario;
import br.com.cajumobile.delivery.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestParam("login") String login, @RequestParam("senha") String senha) {
        try {
            return new ResponseEntity<>(usuarioService.login(login, senha), HttpStatus.OK);
        } catch (EmptyResultDataAccessException | NoResultException noResultException) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> saveUsuario(@RequestBody Usuario usuario) {
        try {
            return new ResponseEntity<>(usuarioService.saveUser(usuario), HttpStatus.OK);
        } catch (EmptyResultDataAccessException | NoResultException noResultException) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (DataIntegrityViolationException exc){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
