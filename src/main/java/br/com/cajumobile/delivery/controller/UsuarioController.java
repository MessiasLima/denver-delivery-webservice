package br.com.cajumobile.delivery.controller;

import br.com.cajumobile.delivery.configuration.SecurityConfiguration;
import br.com.cajumobile.delivery.exception.EntityNotFoundException;
import br.com.cajumobile.delivery.exception.NoPermissionException;
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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam("login") String login, @RequestParam("senha") String senha) {
        try {
            return new ResponseEntity<>(usuarioService.login(login, senha), HttpStatus.OK);
        } catch (EmptyResultDataAccessException | NoResultException noResultException) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping
    public ResponseEntity<?> saveUsuario(@RequestBody Usuario usuario) {
        try {
            return new ResponseEntity<>(usuarioService.saveUser(usuario), HttpStatus.OK);
        } catch (EmptyResultDataAccessException | NoResultException noResultException) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (DataIntegrityViolationException exc) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/adm-sistema")
    public ResponseEntity<?> listUsuariosAdmSistema() {
        return ResponseEntity.ok(usuarioService.listAdmSistema());
    }

    @GetMapping("/estabelecimento/{idEstabelecimento}")
    public ResponseEntity<?> listUsuariosByEstabelecimento(@PathVariable("idEstabelecimento") Integer idEstabelecimento) {
        try {
            return new ResponseEntity<>(usuarioService.listByEstabelecimento(idEstabelecimento), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<?> deleteusuario(@RequestAttribute(SecurityConfiguration.AUTHETICATED_USER) Usuario usuario, @PathVariable("idUsuario") Integer idUsuario) {
        try {
            usuarioService.delete(usuario, idUsuario);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getCauseDTO());
        } catch (NoPermissionException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }catch (DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        }

    }
}
