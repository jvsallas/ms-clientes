package br.com.mercadosallas.clientes.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity(name = "cliente")
public class ClienteEntity {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String nome;
    private String sobrenome;
    private LocalDate dataNascimento;
    private String cpf;
    private LocalDate dataCadastro = LocalDate.now();

}
