package br.com.mercadosallas.clientes.controller;

import br.com.mercadosallas.clientes.exception.ClienteNaoEncontradoException;
import br.com.mercadosallas.clientes.dto.ClienteForm;
import br.com.mercadosallas.clientes.dto.ClienteDto;
import br.com.mercadosallas.clientes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteDto> adicionarCliente(@Valid @RequestBody ClienteForm clienteForm) {

        ClienteDto clienteDto = clienteService.adicionarCliente(clienteForm);

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteDto);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDto>> listarClientes() {

        List<ClienteDto> clientes = clienteService.listarClientes();

        return ResponseEntity.status(HttpStatus.OK).body(clientes);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> listarCliente(@PathVariable String id) {
        try {

            ClienteDto clienteDto = clienteService.listarCliente(id);

            return ResponseEntity.status(HttpStatus.OK).body(clienteDto);

        } catch (Exception ex) {

            if (ex instanceof ClienteNaoEncontradoException)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
            else
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }
}
