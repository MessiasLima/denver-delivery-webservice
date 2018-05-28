package br.com.cajumobile.delivery.model;

import br.com.cajumobile.delivery.model.enun.StatusCliente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente {

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
    private StatusCliente status;

    @Getter
    @Setter
    @Column(name = "identificador_externo")
    private String identificadorExterno;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String token;
}
