package br.com.cajumobile.delivery.model;


import br.com.cajumobile.delivery.model.enun.TipoUsuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String nome;

    @Getter
    @Setter
    @Column(unique = true)
    private String login;

    @Getter
    @Setter
    private String senha;

    @Getter
    @Setter
    private String token;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;

    @Getter
    @Setter
    private String email;

}
