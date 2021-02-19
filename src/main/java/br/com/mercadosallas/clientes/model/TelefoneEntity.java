package br.com.mercadosallas.clientes.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "telefone")
public class TelefoneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ddd")
    private String ddd;

    @Column(name = "numero", unique = true)
    private String numero;

    @Column(name = "tipo")
    private String tipo;
}
