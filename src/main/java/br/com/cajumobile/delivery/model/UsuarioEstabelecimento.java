package br.com.cajumobile.delivery.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario_estabelecimento")
public class UsuarioEstabelecimento {
    @Id
    @Getter
    @Setter
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Getter
    @Setter
    @Column(name = "id_estabelecimento")
    private Integer idEstabelecimento;
}
