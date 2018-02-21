package br.com.cajumobile.delivery.service;

import br.com.cajumobile.delivery.model.Estabelecimento;
import br.com.cajumobile.delivery.model.Usuario;
import br.com.cajumobile.delivery.model.enun.TipoUsuario;
import br.com.cajumobile.delivery.repository.EstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EstabelecimentoService {

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    public List<Estabelecimento> listByUsuario(Usuario usuario) {
        List<Estabelecimento> estabelecimentos = listByUsuarioType(usuario);
        estabelecimentos.forEach(estabelecimento -> {
            estabelecimento.setCidade(cidadeService.findById(estabelecimento.getIdCidade()));
        });
        return estabelecimentos;
    }

    private List<Estabelecimento> listByUsuarioType(Usuario usuario) {
        if (usuario.getTipo().equals(TipoUsuario.ADM_SISTEMA)){
            return estabelecimentoRepository.listAll();
        }else {
            return estabelecimentoRepository.listByIdUsuario(usuario.getId());
        }
    }

    @Transactional
    public Estabelecimento save(Estabelecimento estabelecimento) {
        return estabelecimentoRepository.saveOrUpdate(estabelecimento);
    }
}
