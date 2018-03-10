package br.com.cajumobile.delivery.service;

import br.com.cajumobile.delivery.exception.EntityNotFoundException;
import br.com.cajumobile.delivery.exception.InvalidFileException;
import br.com.cajumobile.delivery.model.Estabelecimento;
import br.com.cajumobile.delivery.model.Usuario;
import br.com.cajumobile.delivery.model.enun.StatusEstabelecimento;
import br.com.cajumobile.delivery.model.enun.TipoUsuario;
import br.com.cajumobile.delivery.repository.EstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
public class EstabelecimentoService {

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private FileService fileService;

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
        if (usuario.getTipo().equals(TipoUsuario.ADM_SISTEMA)) {
            return estabelecimentoRepository.listAll();
        } else {
            return estabelecimentoRepository.listByIdUsuario(usuario.getId());
        }
    }

    @Transactional
    public Estabelecimento save(Estabelecimento estabelecimento) {
        if (estabelecimento.getStatus() == null) {
            estabelecimento.setStatus(StatusEstabelecimento.ATIVO);
        }
        return estabelecimentoRepository.saveOrUpdate(estabelecimento);
    }

    @Transactional
    public void updateImage(MultipartFile file, Integer idEstabelecimento) throws EntityNotFoundException, InvalidFileException, IOException {
        validateFile(file);
        deleteImageIfExists(idEstabelecimento);
        String fileName = fileService.storeFile(file);
        Estabelecimento estabelecimento = estabelecimentoRepository.findById(idEstabelecimento);
        estabelecimento.setUrlImage(fileName);
        save(estabelecimento);
    }

    private void deleteImageIfExists(Integer idEstabelecimento) throws EntityNotFoundException {
        Estabelecimento estabelecimento = findById(idEstabelecimento);
        if (estabelecimento.getUrlImage() != null){
            fileService.deleteFileIfExists(estabelecimento.getUrlImage());
        }
    }

    public Estabelecimento findById(Integer idEstabelecimento) throws EntityNotFoundException{
        return estabelecimentoRepository.findById(idEstabelecimento);
    }

    private void validateFile(MultipartFile file) throws InvalidFileException {
        String extension = fileService.getExtension(file);
        if ((!extension.equals("jpg")) && (!extension.equals("png"))) {
            throw new InvalidFileException("Invalid extension");
        }
    }
}
