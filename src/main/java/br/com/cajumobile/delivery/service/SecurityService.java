package br.com.cajumobile.delivery.service;

import br.com.cajumobile.delivery.model.Usuario;
import br.com.cajumobile.delivery.model.UsuarioEstabelecimento;
import br.com.cajumobile.delivery.model.enun.TipoUsuario;
import br.com.cajumobile.delivery.repository.UsuarioEstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityService {

    @Autowired
    private UsuarioEstabelecimentoRepository usuarioEstabelecimentoRepository;

    public boolean isUsuarioAdmSistema(Usuario usuario){
        return usuario.getTipo().equals(TipoUsuario.ADM_SISTEMA);
    }

    public boolean isUsuarioFuncionario(Usuario usuario) {
        return usuario.getTipo().equals(TipoUsuario.FUNCIONARIO);
    }

    public boolean isUsuarioAdmOfEstabelecimento(Usuario usuario, Integer idEstabelecimento) {
        boolean result = usuario.getTipo().equals(TipoUsuario.ADM_ESTABELECIMENTO);
        List<UsuarioEstabelecimento> usuarioEstabelecimentos = usuarioEstabelecimentoRepository.findByUsuario(usuario.getId());
        for (UsuarioEstabelecimento  usuarioEstabelecimento : usuarioEstabelecimentos){
            result = usuarioEstabelecimento.getIdEstabelecimento().equals(idEstabelecimento);
        }
        return result;
    }
}
