package br.com.cajumobile.delivery.service;

import br.com.cajumobile.delivery.exception.EntityNotFoundException;
import br.com.cajumobile.delivery.exception.InvalidFileException;
import br.com.cajumobile.delivery.exception.NoPermissionException;
import br.com.cajumobile.delivery.model.Produto;
import br.com.cajumobile.delivery.model.Usuario;
import br.com.cajumobile.delivery.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private TipoProdutoService tipoProdutoService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private FileService fileService;

    @Transactional
    public Produto save(Usuario usuario, Produto produto) throws NoPermissionException {
        verifyPermissionsUsuarioEstabelecimento(usuario, produto.getIdEstabelecimento());
        return produtoRepository.saveOrUpdate(produto);
    }

    public List<Produto> findAll(Usuario usuario) throws EntityNotFoundException, NoPermissionException {
        verifyPermissionsFindAll(usuario);
        List<Produto> produtos = produtoRepository.findAll();
        for (Produto produto : produtos) {
            setTipoProduto(produto);
        }
        return produtos;
    }

    private void verifyPermissionsFindAll(Usuario usuario) throws NoPermissionException {
        if (!securityService.isUsuarioAdmSistema(usuario)) {
            throw new NoPermissionException();
        }
    }

    private void setTipoProduto(Produto produto) throws EntityNotFoundException {
        produto.setTipoProduto(tipoProdutoService.findById(produto.getIdTipoProduto()));
    }

    public List<Produto> findByEstabelecimento(Usuario usuario, Integer idEstabelecimento) throws EntityNotFoundException, NoPermissionException {
        verifyPermissionsUsuarioEstabelecimento(usuario, idEstabelecimento);
        List<Produto> produtos = produtoRepository.findByEstabelecimento(idEstabelecimento);
        for (Produto produto : produtos) {
            setTipoProduto(produto);
        }
        return produtos;
    }

    private void verifyPermissionsUsuarioEstabelecimento(Usuario usuario, Integer idEstabelecimento) throws NoPermissionException {
        if (securityService.isUsuarioFuncionario(usuario)) {
            throw new NoPermissionException();
        }

        if (!securityService.isUsuarioAdmSistema(usuario)){
            if (!securityService.isUsuarioAdmOfEstabelecimento(usuario, idEstabelecimento)){
                throw new NoPermissionException();
            }
        }
    }

    @Transactional
    public void updateImage(MultipartFile file, Integer idProduto) throws InvalidFileException, EntityNotFoundException, IOException {
        fileService.validateImageFile(file);
        Produto produto = findById(idProduto);
        deleteImageIfExists(produto);
        String fileName = fileService.storeFile(file);
        produto.setImagem(fileName);
        produtoRepository.save(produto);
    }

    private void deleteImageIfExists(Produto produto) throws EntityNotFoundException {
        if (produto.getImagem() != null){
            fileService.deleteFileIfExists(produto.getImagem());
        }
    }

    public Produto findById(Integer idProduto) throws EntityNotFoundException {
        return produtoRepository.findById(idProduto);
    }
}
