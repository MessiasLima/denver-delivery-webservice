package br.com.cajumobile.delivery.service;

import br.com.cajumobile.delivery.exception.EntityNotFoundException;
import br.com.cajumobile.delivery.exception.NoPermissionException;
import br.com.cajumobile.delivery.model.Usuario;
import br.com.cajumobile.delivery.model.UsuarioEstabelecimento;
import br.com.cajumobile.delivery.model.enun.TipoUsuario;
import br.com.cajumobile.delivery.repository.UsuarioEstabelecimentoRepository;
import br.com.cajumobile.delivery.repository.UsuarioRepository;
import br.com.cajumobile.delivery.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioEstabelecimentoRepository usuarioEstabelecimentoRepository;

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
        if (usuario.getId() == null) {
            usuario.setSenha(Utils.md5(usuario.getSenha()));
        }
        return usuarioRepository.saveOrUpdate(usuario);
    }

    public List<Usuario> listAdmSistema() {
        return usuarioRepository.listAdmSistema();
    }

    public List<Usuario> listByEstabelecimento(Integer idEstabelecimento) throws EntityNotFoundException {
        List<UsuarioEstabelecimento> usuariosEstabelecimento = usuarioEstabelecimentoRepository.listByIdEstabelecimento(idEstabelecimento);
        if (usuariosEstabelecimento.isEmpty()) {
            throw new EntityNotFoundException(usuariosEstabelecimento.getClass(), 0);
        }
        return usuarioRepository.findByIds(getIds(usuariosEstabelecimento));
    }

    private List<Integer> getIds(List<UsuarioEstabelecimento> usuariosEstabelecimento) {
        List<Integer> integers = new ArrayList<>();
        usuariosEstabelecimento.forEach(usuarioEstabelecimento -> integers.add(usuarioEstabelecimento.getIdUsuario()));
        return integers;
    }


    @Transactional
    public void delete(Usuario userAuthenticated, Integer idUsuario) throws EntityNotFoundException, NoPermissionException {
        Usuario usuarioToDelete = usuarioRepository.findById(idUsuario);
        if (hasPermissionToDelete(userAuthenticated, usuarioToDelete)) {
            usuarioEstabelecimentoRepository.deleteByUsuario(usuarioToDelete.getId());
            usuarioRepository.delete(usuarioToDelete);
        } else {
            throw new NoPermissionException();
        }
    }

    private boolean hasPermissionToDelete(Usuario userAuthenticated, Usuario usuarioToDelete) {
        switch (userAuthenticated.getTipo()) {
            case ADM_SISTEMA:
                return true;
            case ADM_ESTABELECIMENTO:
                return !usuarioToDelete.getTipo().equals(TipoUsuario.ADM_SISTEMA);
            case FUNCIONARIO:
                return false;
            default:
                return false;
        }
    }
}
