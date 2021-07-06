package br.com.mercadosallas.clientes.service;

import br.com.mercadosallas.clientes.dto.ClienteAtualizacaoForm;
import br.com.mercadosallas.clientes.dto.ClienteDto;
import br.com.mercadosallas.clientes.dto.ClienteForm;
import br.com.mercadosallas.clientes.exception.exceptions.ClienteNotFoundException;
import br.com.mercadosallas.clientes.exception.exceptions.CpfAlreadyExistsException;
import br.com.mercadosallas.clientes.exception.exceptions.EmailAlreadyExistsException;
import br.com.mercadosallas.clientes.exception.exceptions.InvalidEmailException;
import br.com.mercadosallas.clientes.mapper.ClienteMapper;
import br.com.mercadosallas.clientes.model.ClienteEntity;
import br.com.mercadosallas.clientes.repository.ClienteRepository;
import br.com.mercadosallas.utils.DataUtils;
import br.com.mercadosallas.utils.ValidadorCpf;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static br.com.mercadosallas.utils.StringUtils.isNotNullOrBlank;

@Service
public class ClienteService {

    private static final Logger log = LoggerFactory.getLogger(ClienteService.class);

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public ClienteDto adicionarCliente(ClienteForm clienteForm) {

        log.info("Validando registro de cliente por cpf e email.");

        ValidadorCpf.validar(clienteForm.getCpf());

        validarCpfJaCadastrado(clienteForm.getCpf());

        validarEmailJaCadastrado(clienteForm.getEmail());

        ClienteEntity clienteEntity = ClienteMapper.mapToEntity(clienteForm);

        ClienteEntity entity = clienteRepository.save(clienteEntity);

        log.info("Cliente cadastrado com sucesso na base de dados.");

        return ClienteMapper.mapToDto(entity);
    }

    public List<ClienteDto> listarClientes() {

        log.info("Listando todos clientes.");

        List<ClienteEntity> clientes = clienteRepository.findAll();

        if (clientes.isEmpty())
            throw new ClienteNotFoundException("Nenhum cliente encontrado;");

        return ClienteMapper.mapToListDto(clientes);
    }

    public ClienteDto listarClientePorId(String id) {

        ClienteEntity clienteEntity = buscarClientePorId(id);

        return ClienteMapper.mapToDto(clienteEntity);
    }

    @Transactional
    public ClienteDto alterarDadosCliente(String id, ClienteAtualizacaoForm form) {

        log.info("Alterando dados do cliente.");

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

        log.info("Listando cliente pelo CPF.");

        ClienteEntity clienteEntity = buscarClientePorCpf(cpf);

        return ClienteMapper.mapToDto(clienteEntity);
    }

    public void deletarCliente(String id) {

        log.info("Deletando cliente: {}", id);

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

