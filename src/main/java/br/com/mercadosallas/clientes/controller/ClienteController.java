package br.com.mercadosallas.clientes.controller;

import br.com.mercadosallas.clientes.dto.AtualizacaoClienteForm;
import br.com.mercadosallas.clientes.dto.ClienteDto;
import br.com.mercadosallas.clientes.dto.ClienteForm;
import br.com.mercadosallas.clientes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    @Transactional
    public ResponseEntity<ClienteDto> adicionarCliente(@RequestBody @Valid ClienteForm clienteForm) {

        ClienteDto clienteDto = clienteService.adicionarCliente(clienteForm);

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteDto);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDto>> listarClientes() {

        List<ClienteDto> clientes = clienteService.listarClientes();

        return ResponseEntity.status(HttpStatus.OK).body(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarClientePorId(@PathVariable String id) {

        ClienteDto clienteDto = clienteService.listarClientePorId(id);

        return ResponseEntity.status(HttpStatus.OK).body(clienteDto);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<?> listarClientePorCpf(@PathVariable String cpf) {

        ClienteDto clienteDto = clienteService.listarClientePorCpf(cpf);

        return ResponseEntity.status(HttpStatus.OK).body(clienteDto);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ClienteDto> alterarDadosCliente(@PathVariable String id, @RequestBody @Valid AtualizacaoClienteForm form){
        ClienteDto clienteDto = clienteService.alterarDadosCliente(id, form);
        return ResponseEntity.status(HttpStatus.OK).body(clienteDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarCliente(@PathVariable String id){
        clienteService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
