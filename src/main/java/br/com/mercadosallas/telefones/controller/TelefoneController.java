package br.com.mercadosallas.telefones.controller;

import br.com.mercadosallas.telefones.dto.TelefoneAtualizacaoForm;
import br.com.mercadosallas.telefones.dto.TelefoneDto;
import br.com.mercadosallas.telefones.dto.TelefoneForm;
import br.com.mercadosallas.telefones.service.TelefoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/clientes/{idCliente}/telefones")
public class TelefoneController {

    @Autowired
    private TelefoneService telefoneService;

    @PostMapping
    @Transactional
    public ResponseEntity<TelefoneDto> adicionarTelefoneAoCliente(@PathVariable String idCliente,
                                                             @RequestBody TelefoneForm form) {
        TelefoneDto telefoneDto = telefoneService.adicionarTelefoneAoCliente(idCliente, form);
        return ResponseEntity.status(HttpStatus.CREATED).body(telefoneDto);
    }

    @GetMapping
    public ResponseEntity<List<TelefoneDto>> listarTelefonesDoCliente(@PathVariable String idCliente) {
        List<TelefoneDto> telefones = telefoneService.listarTelefonesDoCliente(idCliente);
        return ResponseEntity.status(HttpStatus.OK).body(telefones);
    }

    @GetMapping("/{idTelefone}")
    public ResponseEntity<TelefoneDto> listarTelefonePorId(@PathVariable String idCliente, @PathVariable Long idTelefone) {
        TelefoneDto telefone = telefoneService.listarTelefonePorId(idCliente, idTelefone);
        return ResponseEntity.status(HttpStatus.OK).body(telefone);
    }

    @PutMapping("/{idTelefone}")
    @Transactional
    public ResponseEntity<TelefoneDto> alterarNumeroTelefone(@PathVariable String idCliente, @PathVariable Long idTelefone,
                                                             @RequestBody TelefoneAtualizacaoForm form) {
        TelefoneDto telefoneDto = telefoneService.alterarTelefone(idCliente, idTelefone, form);
        return ResponseEntity.status(HttpStatus.OK).body(telefoneDto);
    }

    @DeleteMapping("/{idTelefone}")
    @Transactional
    public ResponseEntity<?> deletarTelefone(@PathVariable String idCliente, @PathVariable Long idTelefone) {
        telefoneService.deletarTelefone(idCliente, idTelefone);
        return ResponseEntity.noContent().build();
    }
}
