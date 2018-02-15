package br.com.cajumobile.delivery.service;

import br.com.cajumobile.delivery.exception.NoPermissionException;
import br.com.cajumobile.delivery.model.Cidade;
import br.com.cajumobile.delivery.model.Usuario;
import br.com.cajumobile.delivery.model.enun.TipoUsuario;
import br.com.cajumobile.delivery.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Transactional
    public void saveCidade(Usuario usuario, String nomeCidade) throws NoPermissionException {
        if (!usuario.getTipo().equals(TipoUsuario.ADM_SISTEMA)){
            throw new NoPermissionException();
        }

        Cidade cidade = new Cidade();
        cidade.setNome(nomeCidade);

        cidadeRepository.save(cidade);
    }

    public List<Cidade> listCidades() {
        return cidadeRepository.listCidades();
    }

    @Transactional
    public Cidade updateCidade(Cidade cidade) {
        return cidadeRepository.saveOrUpdate(cidade);
    }
}
