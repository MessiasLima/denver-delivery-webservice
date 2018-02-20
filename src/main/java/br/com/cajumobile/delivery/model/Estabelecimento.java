package br.com.cajumobile.delivery.model;

import br.com.cajumobile.delivery.model.enun.StatusEstabelecimento;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "estabelecimento")
public class Estabelecimento {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Getter
    @Setter
    private String nome;

    @Getter
    @Setter
    private String descricao;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private StatusEstabelecimento status;

    @Getter
    @Setter
    private Double latitude;

    @Getter
    @Setter
    private Double longitude;

    @Getter
    @Setter
    @Column(name = "id_cidade")
    private Integer idCidade;

    @Getter
    @Setter
    @Column(name = "url_image")
    private String urlImage;

    @Getter
    @Setter
    @Transient
    private Cidade cidade;
}
