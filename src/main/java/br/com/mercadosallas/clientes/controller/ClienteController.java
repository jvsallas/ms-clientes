package br.com.mercadosallas.clientes.controller;

import br.com.mercadosallas.clientes.dto.ClienteAtualizacaoPatchForm;
import br.com.mercadosallas.clientes.dto.ClienteAtualizacaoPutForm;
import br.com.mercadosallas.clientes.dto.ClienteDto;
import br.com.mercadosallas.clientes.dto.ClienteForm;
import br.com.mercadosallas.clientes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    @Transactional(readOnly = true)
    public ResponseEntity<Object> listarClientes(@RequestParam(required = false) String cpf) {

        if (cpf != null)
            return ResponseEntity.status(HttpStatus.OK).body(clienteService.listarClientePorCpf(cpf));

        return ResponseEntity.status(HttpStatus.OK).body(clienteService.listarClientes());
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<ClienteDto> listarClientePorId(@PathVariable String id) {

        ClienteDto clienteDto = clienteService.listarClientePorId(id);

        return ResponseEntity.status(HttpStatus.OK).body(clienteDto);
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<ClienteDto> alterarDadosCliente(@PathVariable String id, @RequestBody ClienteAtualizacaoPatchForm form) {
        ClienteDto clienteDto = clienteService.alterarDadosCliente(id, form);
        return ResponseEntity.status(HttpStatus.OK).body(clienteDto);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ClienteDto> alterarTodosDadosCliente(@PathVariable String id, @RequestBody @Valid ClienteAtualizacaoPutForm form) {
        ClienteDto clienteDto = clienteService.alterarDadosCliente(id, form);
        return ResponseEntity.status(HttpStatus.OK).body(clienteDto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletarCliente(@PathVariable String id) {
        clienteService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
