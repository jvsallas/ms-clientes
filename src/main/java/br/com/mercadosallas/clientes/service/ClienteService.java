package br.com.mercadosallas.clientes.service;

import br.com.mercadosallas.clientes.dto.ClienteAtualizacaoPatchForm;
import br.com.mercadosallas.clientes.dto.ClienteAtualizacaoPutForm;
import br.com.mercadosallas.clientes.dto.ClienteDto;
import br.com.mercadosallas.clientes.dto.ClienteForm;
import br.com.mercadosallas.clientes.exception.exceptions.ClienteNotFoundException;
import br.com.mercadosallas.clientes.exception.exceptions.CpfAlreadyExistsException;
import br.com.mercadosallas.clientes.exception.exceptions.EmailAlreadyExistsException;
import br.com.mercadosallas.clientes.exception.exceptions.InvalidEmailException;
import br.com.mercadosallas.clientes.mapper.ClienteMapper;
import br.com.mercadosallas.clientes.model.ClienteEntity;
import br.com.mercadosallas.clientes.repository.ClienteRepository;
import br.com.mercadosallas.handler.ClienteExceptionGeneric;
import br.com.mercadosallas.telefones.mapper.TelefoneMapper;
import br.com.mercadosallas.telefones.service.TelefoneService;
import br.com.mercadosallas.utils.DataUtils;
import br.com.mercadosallas.utils.ValidadorCpf;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static br.com.mercadosallas.utils.StringUtils.isNotNullOrBlank;
import static java.util.Objects.nonNull;

@Service
public class ClienteService {

    private static final Logger log = LoggerFactory.getLogger(ClienteService.class);

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TelefoneService telefoneService;

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

    public Object listarClientes(String cpf) {
        if (cpf != null)
            return listarClientePorCpf(cpf);

        log.info("Listando todos clientes.");

        List<ClienteEntity> clientes = clienteRepository.findAll();

        if (clientes.isEmpty())
            throw new ClienteNotFoundException("Nenhum cliente encontrado;");

        return ClienteMapper.mapToListDto(clientes);
    }

    public ClienteDto listarClientePorCpf(String cpf) {

        log.info("Listando cliente pelo CPF.");

        ClienteEntity clienteEntity = buscarClientePorCpf(cpf);

        return ClienteMapper.mapToDto(clienteEntity);
    }

    public ClienteDto listarClientePorId(String id) {

        ClienteEntity clienteEntity = buscarClientePorId(id);

        return ClienteMapper.mapToDto(clienteEntity);
    }

    @Transactional
    public ClienteDto alterarDadosCliente(String id, ClienteAtualizacaoPatchForm form) {

        log.info("Alterando dados do cliente.");

        ClienteEntity clienteEntity = buscarClientePorId(id);

        if (isNotNullOrBlank(form.getNome()))
            clienteEntity.setNome(form.getNome());

        if (isNotNullOrBlank(form.getSobrenome()))
            clienteEntity.setSobrenome(form.getSobrenome());

        if (isNotNullOrBlank(form.getDataNascimento()))
            clienteEntity.setDataNascimento(DataUtils.formatar(form.getDataNascimento()));

        if (isNotNullOrBlank(form.getDataNascimento()))
            clienteEntity.setDataNascimento(DataUtils.formatar(form.getDataNascimento()));

        if (isNotNullOrBlank(form.getEmail()))
            clienteEntity.setEmail(form.getEmail());

        return ClienteMapper.mapToDto(clienteEntity);
    }

    @Transactional
    public ClienteDto alterarDadosCliente(String id, @Valid ClienteAtualizacaoPutForm form) {

        log.info("Validando dados de entrada.");

        ClienteEntity dadosExistentes = buscarClientePorId(id);

        if (nonNull(form.getCpf()) && !Objects.equals(form.getCpf(), dadosExistentes.getCpf()))
            throw new ClienteExceptionGeneric("Não é possivel alterar o CPF", 409);

        if (nonNull(form.getEmail()) && !Objects.equals(form.getEmail(), dadosExistentes.getEmail()))
            throw new ClienteExceptionGeneric("Não é possivel alterar o email", 409);

        log.info("Alterando dados do cliente.");

        dadosExistentes.setNome(form.getNome());
        dadosExistentes.setSobrenome(form.getSobrenome());
        dadosExistentes.setDataNascimento(DataUtils.formatar(form.getDataNascimento()));
        dadosExistentes.setEmail(form.getEmail());

        Optional.of(dadosExistentes.getTelefones()).ifPresent(List::clear);

        dadosExistentes.getTelefones().addAll(TelefoneMapper.mapToListEntity(form.getTelefones()));

        ClienteEntity entity = clienteRepository.save(dadosExistentes);

        log.info("Cliente alterado com sucesso na base de dados.");

        return ClienteMapper.mapToDto(entity);
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

