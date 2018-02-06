package br.com.cajumobile.delivery.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_produto")
public class TipoProduto {

    @Id
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String nome;
}
