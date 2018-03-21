package br.com.cajumobile.delivery.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "produto")
public class Produto {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter
    @Setter
    private String nome;

    @Getter
    @Setter
    private String descricao;

    @Getter
    @Setter
    @Column(name = "id_estabelecimento")
    private Integer idEstabelecimento;

    @Getter
    @Setter
    @Column(name = "id_tipo")
    private Integer idTipoProduto;

    @Getter
    @Setter
    @Transient
    private TipoProduto tipoProduto;

    @Getter
    @Setter
    private Double valor;

    @Getter
    @Setter
    @Column(name = "url_imagem")
    private String imagem;
}
