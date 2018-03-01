package br.com.cajumobile.delivery.repository;

import br.com.cajumobile.delivery.model.UsuarioEstabelecimento;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioEstabelecimentoRepository extends GenericRepository<UsuarioEstabelecimento> {
    public List<UsuarioEstabelecimento> listByIdEstabelecimento(Integer idEstabelecimento) {
        return entityManager.createQuery("FROM UsuarioEstabelecimento ue WHERE ue.idEstabelecimento = :id", UsuarioEstabelecimento.class)
                .setParameter("id",idEstabelecimento )
                .getResultList();
    }

    public void deleteByUsuario(Integer idUsuario) {
        entityManager.createQuery("DELETE FROM UsuarioEstabelecimento ue WHERE ue.idUsuario = :id")
                .setParameter("id", idUsuario)
                .executeUpdate();
    }
}
