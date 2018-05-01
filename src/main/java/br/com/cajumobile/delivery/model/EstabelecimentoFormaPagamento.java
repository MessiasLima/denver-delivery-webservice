package br.com.cajumobile.delivery.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

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

    public EstabelecimentoFormaPagamento(){}

    public EstabelecimentoFormaPagamento(Integer idFormaPagamento, Integer idEstabelecimento) {
        this.idFormaPagamento = idFormaPagamento;
        this.idEstabelecimento = idEstabelecimento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstabelecimentoFormaPagamento that = (EstabelecimentoFormaPagamento) o;
        return Objects.equals(idEstabelecimento, that.idEstabelecimento) &&
                Objects.equals(idFormaPagamento, that.idFormaPagamento);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idEstabelecimento, idFormaPagamento);
    }
}
