package br.com.cajumobile.delivery.repository;

import br.com.cajumobile.delivery.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioRepository extends GenericRepository<Usuario> {

    public UsuarioRepository() {
        super(Usuario.class);
    }

    public Usuario findByAuthorization(String authorization) {
        return entityManager.createQuery("FROM Usuario u WHERE u.token = :authorization", Usuario.class)
                .setParameter("authorization", authorization)
                .getSingleResult();
    }

    public Usuario findByLoginAndSenha(String login, String senha) {
        return entityManager.createQuery("FROM Usuario u WHERE u.login = :login AND u.senha = :senha", Usuario.class)
                .setParameter("login", login)
                .setParameter("senha", senha)
                .getSingleResult();
    }

    public List<Usuario> listAdmSistema() {
        return entityManager.createQuery("FROM Usuario u WHERE u.tipo = 'ADM_SISTEMA'", Usuario.class)
                .getResultList();
    }

    public List<Usuario> findByIds(List<Integer> ids) {
        return entityManager.createQuery("FROM Usuario u WHERE u.id in(:ids)", Usuario.class)
                .setParameter("ids", ids)
                .getResultList();
    }
}
