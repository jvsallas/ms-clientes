package br.com.mercadosallas.clientes.service;

import br.com.mercadosallas.clientes.dto.AtualizacaoClienteForm;
import br.com.mercadosallas.clientes.dto.ClienteDto;
import br.com.mercadosallas.clientes.dto.ClienteForm;
import br.com.mercadosallas.clientes.validation.exception.ClienteNaoEncontradoException;
import br.com.mercadosallas.clientes.mapper.ClienteMapper;
import br.com.mercadosallas.clientes.model.ClienteEntity;
import br.com.mercadosallas.clientes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteDto adicionarCliente(ClienteForm clienteForm) {

        ClienteEntity clienteEntity = ClienteMapper.mapToEntity(clienteForm);

        ClienteEntity entity = clienteRepository.save(clienteEntity);

        return ClienteMapper.mapToDto(entity);
    }

    public List<ClienteDto> listarClientes() {

        List<ClienteEntity> clientes = clienteRepository.findAll();

        return ClienteMapper.mapToListDto(clientes);

    }

    public ClienteDto listarCliente(String id) {

        ClienteEntity clienteEntity = validarClienteExistente(id);

        return ClienteMapper.mapToDto(clienteEntity);
    }

    public ClienteDto alterarDadosCliente(String id, AtualizacaoClienteForm form) {

        ClienteEntity clienteExistente = validarClienteExistente(id);

        ClienteEntity clienteEntity = ClienteMapper.mapFromAtualizacao(form, clienteExistente);

        return ClienteMapper.mapToDto(clienteEntity);
    }

    public void deletarCliente(String id) {
        validarClienteExistente(id);

        clienteRepository.deleteById(id);
    }

    private ClienteEntity validarClienteExistente(String id) {
        Optional<ClienteEntity> clienteOpt = clienteRepository.findById(id);

        if (clienteOpt.isPresent())
            return clienteOpt.get();
        else
            throw new ClienteNaoEncontradoException(String.format("Cliente não encontrado para o id %s", id));
    }
}
