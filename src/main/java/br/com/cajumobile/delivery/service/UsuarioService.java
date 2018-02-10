package br.com.cajumobile.delivery.service;

import br.com.cajumobile.delivery.model.Usuario;
import br.com.cajumobile.delivery.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario findByAuthorization(String authorization) {
        return usuarioRepository.findByAuthorization(authorization);
    }
}
