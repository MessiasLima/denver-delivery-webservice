package br.com.cajumobile.delivery.repository;

import br.com.cajumobile.delivery.model.Estabelecimento;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EstabelecimentoRepository extends GenericRepository<Estabelecimento>{

    private static final String QUERY_SELECT_ESTABELECIMETOS_BY_ID_USUARIO =  "" +
            "SELECT e.* " +
            "FROM estabelecimento e " +
            "INNER JOIN  usuario_estabelecimento ue " +
            "      ON ue.id_estabelecimento = e.id " +
            "WHERE ue.id_usuario = :idUsuario";

    protected EstabelecimentoRepository() {
        super(Estabelecimento.class);
    }

    public List<Estabelecimento> listAll() {
        return entityManager.createQuery("FROM Estabelecimento", Estabelecimento.class).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Estabelecimento> listByIdUsuario(Integer id) {
        return (List<Estabelecimento>) entityManager.createNativeQuery(QUERY_SELECT_ESTABELECIMETOS_BY_ID_USUARIO, Estabelecimento.class)
                .setParameter("idUsuario", id)
                .getResultList();
    }
}
