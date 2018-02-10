package br.com.cajumobile.delivery.service;

import br.com.cajumobile.delivery.model.Usuario;
import br.com.cajumobile.delivery.repository.UsuarioRepository;
import br.com.cajumobile.delivery.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario findByAuthorization(String authorization) {
        return usuarioRepository.findByAuthorization(authorization);
    }

    @Transactional
    public Usuario login(String login, String senha) {
        Usuario usuario = usuarioRepository.findByLoginAndSenha(login, Utils.md5(senha));
        usuario.setToken(generateAuthorization(login, senha));
        return usuarioRepository.saveOrUpdate(usuario);
    }

    private String generateAuthorization(String login, String senha) {
        return Utils.md5(new Date().getTime() + login + senha + System.currentTimeMillis());
    }

    @Transactional
    public Usuario saveUser(Usuario usuario) {
        usuario.setSenha(Utils.md5(usuario.getSenha()));
        return usuarioRepository.saveOrUpdate(usuario);
    }
}
