package br.com.mercadosallas.clientes.service;

import br.com.mercadosallas.clientes.dto.*;
import br.com.mercadosallas.clientes.exception.exceptions.ClienteNotFoundException;
import br.com.mercadosallas.clientes.exception.exceptions.CpfAlreadyExistsException;
import br.com.mercadosallas.clientes.exception.exceptions.EmailAlreadyExistsException;
import br.com.mercadosallas.clientes.exception.exceptions.InvalidEmailException;
import br.com.mercadosallas.clientes.mapper.ClienteMapper;
import br.com.mercadosallas.clientes.model.ClienteEntity;
import br.com.mercadosallas.clientes.repository.ClienteRepository;
import br.com.mercadosallas.utils.DataUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static br.com.mercadosallas.utils.StringUtils.isNotNullOrBlank;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteDto adicionarCliente(ClienteForm clienteForm) {

        validarCpfJaCadastrado(clienteForm.getCpf());

        validarEmailJaCadastrado(clienteForm.getEmail());

        ClienteEntity clienteEntity = ClienteMapper.mapToEntity(clienteForm);

        ClienteEntity entity = clienteRepository.save(clienteEntity);

        return ClienteMapper.mapToDto(entity);
    }



    public List<ClienteDto> listarClientes() {

        List<ClienteEntity> clientes = clienteRepository.findAll();

        if (clientes.isEmpty())
            throw new ClienteNotFoundException("Nenhum cliente encontrado;");

        return ClienteMapper.mapToListDto(clientes);

    }

    public ClienteDto listarClientePorId(String id) {

        ClienteEntity clienteEntity = buscarClientePorId(id);

        return ClienteMapper.mapToDto(clienteEntity);
    }

    public ClienteDto alterarDadosCliente(String id, ClienteAtualizacaoForm form) {

        ClienteEntity clienteEntity = buscarClientePorId(id);

        if (isNotNullOrBlank(form.getNome()))
            clienteEntity.setNome(form.getNome());

        if (isNotNullOrBlank(form.getSobrenome()))
            clienteEntity.setSobrenome(form.getSobrenome());

        if (isNotNullOrBlank(form.getDataNascimento()))
            clienteEntity.setDataNascimento(DataUtils.formatar(form.getDataNascimento()));

        return ClienteMapper.mapToDto(clienteEntity);
    }

    public ClienteDto listarClientePorCpf(String cpf) {

        ClienteEntity clienteEntity = buscarClientePorCpf(cpf);

        return ClienteMapper.mapToDto(clienteEntity);
    }

    public void deletarCliente(String id) {
        buscarClientePorId(id);

        clienteRepository.deleteById(id);
    }

    public ClienteEntity buscarClientePorId(String id) {
        Optional<ClienteEntity> clienteOpt = clienteRepository.findById(id);

        return clienteOpt.orElseThrow(() ->
                new ClienteNotFoundException(String.format("Cliente não encontrado para o id %s", id)));
    }

    public ClienteEntity buscarClientePorCpf(String cpf) {
        Optional<ClienteEntity> clienteOpt = clienteRepository.findByCpf(cpf);

        return clienteOpt.orElseThrow(
                () -> new ClienteNotFoundException(String.format("Cliente não encontrado para o cpf %s", cpf)));
    }

    private void validarCpfJaCadastrado(String cpf) {
        Optional<ClienteEntity> clienteOptByCpf = clienteRepository.findByCpf(cpf);

        if (clienteOptByCpf.isPresent())
            throw new CpfAlreadyExistsException("CPF informado já cadastrado.");
    }

    private void validarEmailJaCadastrado(String email) {
        Optional<ClienteEntity> clienteOptByEmail = clienteRepository.findByEmail(email);

        if (clienteOptByEmail.isPresent())
            throw new EmailAlreadyExistsException("Email informado já cadastrado.");
    }


    private boolean emailIsValid(String email) {
        EmailValidator emailValidator = EmailValidator.getInstance();
        if (emailValidator.isValid(email))
            return true;
        throw new InvalidEmailException("Email inválido.");
    }

}

