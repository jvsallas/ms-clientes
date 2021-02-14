package br.com.mercadosallas.clientes.controller;

import br.com.mercadosallas.clientes.model.ClienteRequestDto;
import br.com.mercadosallas.clientes.model.ClienteResponseDto;
import br.com.mercadosallas.clientes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/clientes")
@CrossOrigin
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponseDto> adicionarCliente(@Valid @RequestBody ClienteRequestDto clienteRequestDto) {

        ClienteResponseDto clienteResponseDto = clienteService.adicionarCliente(clienteRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDto>> listarClientes() {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.listarClientes());
    }
}
