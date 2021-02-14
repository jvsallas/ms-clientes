package br.com.mercadosallas.clientes.service;

import br.com.mercadosallas.clientes.mapper.ClienteMapper;
import br.com.mercadosallas.clientes.model.ClienteEntity;
import br.com.mercadosallas.clientes.dto.ClienteForm;
import br.com.mercadosallas.clientes.dto.ClienteDto;
import br.com.mercadosallas.clientes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteDto adicionarCliente(ClienteForm clienteForm) {

        ClienteEntity entity = clienteRepository.save(ClienteMapper.mapToEntity(clienteForm));

        return new ClienteDto(entity);
    }

    public List<ClienteDto> listarClientes() {

        List<ClienteEntity> clientes = clienteRepository.findAll();

        return ClienteDto.converter(clientes);

    }

    public ClienteDto listarCliente(String id) {
        Optional<ClienteEntity> clienteOpt = clienteRepository.findById(id);

        if (!clienteOpt.isPresent())
            throw new RuntimeException("Cliente não encontrado");

        return new ClienteDto(clienteOpt.get());
    }
}
