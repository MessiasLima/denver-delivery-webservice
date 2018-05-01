package br.com.cajumobile.delivery.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "estabelecimento_forma_pagamento")
public class EstabelecimentoFormaPagamento implements Serializable {

    @Id
    @Getter
    @Setter
    @Column(name = "id_estabelecimento")
    private Integer idEstabelecimento;

    @Id
    @Getter
    @Setter
    @Column(name = "id_forma_pagamento")
    private Integer idFormaPagamento;
}
