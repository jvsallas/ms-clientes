package br.com.mercadosallas.clientes.controller;

import br.com.mercadosallas.clientes.model.ClienteSaida;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "clientes")
@CrossOrigin
public class ClienteController {

    @PostMapping
    public String adicionarCliente(){
        return "Cliente Cadastrdo com sucesso!";
    }

    @GetMapping
    public ClienteSaida listarClientes(){
        ClienteSaida clienteSaida = new ClienteSaida();
        clienteSaida.setId(UUID.randomUUID().toString());
        return clienteSaida;
    }
}
