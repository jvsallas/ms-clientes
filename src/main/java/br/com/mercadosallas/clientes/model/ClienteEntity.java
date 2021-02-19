package br.com.mercadosallas.clientes.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity(name = "cliente")
public class ClienteEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sobrenome")
    private String sobrenome;

    @Column(name = "dataNascimento")
    private LocalDate dataNascimento;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "email")
    private String email;

    @Column(name = "data_cadastro")
    private LocalDate dataCadastro = LocalDate.now();

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<TelefoneEntity> telefones;

}
