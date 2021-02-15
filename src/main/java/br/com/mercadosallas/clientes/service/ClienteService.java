package br.com.mercadosallas.clientes.service;

import br.com.mercadosallas.clientes.exception.ClienteNaoEncontradoException;
import br.com.mercadosallas.clientes.mapper.ClienteMapper;
import br.com.mercadosallas.clientes.model.ClienteEntity;
import br.com.mercadosallas.clientes.dto.ClienteForm;
import br.com.mercadosallas.clientes.dto.ClienteDto;
import br.com.mercadosallas.clientes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        Optional<ClienteEntity> clienteOpt = clienteRepository.findById(id);

        if (!clienteOpt.isPresent())
            throw new RuntimeException("Cliente não encontrado");

        return ClienteMapper.mapToDto(clienteOpt.get());
    }
}
