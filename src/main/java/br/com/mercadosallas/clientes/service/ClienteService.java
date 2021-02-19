package br.com.mercadosallas.clientes.service;

import br.com.mercadosallas.clientes.dto.*;
import br.com.mercadosallas.clientes.exception.exceptions.ClienteNotFoundException;
import br.com.mercadosallas.clientes.exception.exceptions.CpfAlreadyExistsException;
import br.com.mercadosallas.clientes.exception.exceptions.InvalidEmailException;
import br.com.mercadosallas.clientes.exception.exceptions.TelefoneNotFoundException;
import br.com.mercadosallas.clientes.mapper.ClienteMapper;
import br.com.mercadosallas.clientes.mapper.TelefoneMapper;
import br.com.mercadosallas.clientes.model.ClienteEntity;
import br.com.mercadosallas.clientes.model.TelefoneEntity;
import br.com.mercadosallas.clientes.repository.ClienteRepository;
import br.com.mercadosallas.clientes.utils.DataUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static br.com.mercadosallas.clientes.utils.StringUtils.isNotNullOrBlank;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteDto adicionarCliente(ClienteForm clienteForm) {

        Optional<ClienteEntity> clienteByCpf = findByCpf(clienteForm.getCpf());

        if (clienteByCpf.isPresent())
            throw new CpfAlreadyExistsException("CPF informado já cadastrado.");

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

        ClienteEntity clienteEntity = buscarClienteExistente(id);

        return ClienteMapper.mapToDto(clienteEntity);
    }

    public ClienteDto alterarDadosCliente(String id, ClienteAtualizacaoForm form) {

        ClienteEntity clienteEntity = buscarClienteExistente(id);

        if (isNotNullOrBlank(form.getNome()))
            clienteEntity.setNome(form.getNome());

        if (isNotNullOrBlank(form.getSobrenome()))
            clienteEntity.setSobrenome(form.getSobrenome());

        if (isNotNullOrBlank(form.getDataNascimento()))
            clienteEntity.setDataNascimento(DataUtils.formatar(form.getDataNascimento()));

        return ClienteMapper.mapToDto(clienteEntity);
    }

    public ClienteDto listarClientePorCpf(String cpf) {

        ClienteEntity clienteEntity = buscarPorCpfClienteEValidar(cpf);

        return ClienteMapper.mapToDto(clienteEntity);
    }

    public void deletarCliente(String id) {
        buscarClienteExistente(id);

        clienteRepository.deleteById(id);
    }

    private ClienteEntity buscarClienteExistente(String id) {
        Optional<ClienteEntity> clienteOpt = clienteRepository.findById(id);

        return clienteOpt.orElseThrow(() ->
                new ClienteNotFoundException(String.format("Cliente não encontrado para o id %s", id)));
    }

    private ClienteEntity buscarPorCpfClienteEValidar(String cpf) {
        Optional<ClienteEntity> clienteOpt = findByCpf(cpf);

        return clienteOpt.orElseThrow(
                () -> new ClienteNotFoundException(String.format("Cliente não encontrado para o cpf %s", cpf)));
    }

    private Optional<ClienteEntity> findByCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

    private boolean emailIsValid(String email) {
        EmailValidator emailValidator = EmailValidator.getInstance();
        if (emailValidator.isValid(email))
            return true;
        throw new InvalidEmailException("Email inválido.");
    }

    public List<TelefoneDto> listarTelefones(String idCliente) {
        ClienteEntity clienteEntity = buscarClienteExistente(idCliente);

        return TelefoneMapper.mapToListDto(clienteEntity.getTelefones());
    }

    public TelefoneDto alterarTelefone(String idCliente, Long idTelefone, TelefoneAtualizacaoForm form) {

        ClienteEntity clienteEntity = buscarClienteExistente(idCliente);

        List<TelefoneEntity> telefones = clienteEntity.getTelefones();

        TelefoneEntity telefoneEntity = buscarTelefoneExistente(idTelefone, telefones);

        if (isNotNullOrBlank(form.getDdd()))
            telefoneEntity.setDdd(form.getDdd());

        if (isNotNullOrBlank(form.getNumero()))
            telefoneEntity.setNumero(form.getNumero());

        if (isNotNullOrBlank(form.getTipo()))
            telefoneEntity.setTipo(form.getTipo());

        return TelefoneMapper.mapToDto(telefoneEntity);
    }

    public void deletarTelefone(String idCliente, Long idTelefone) {
        ClienteEntity clienteEntity = buscarClienteExistente(idCliente);

        List<TelefoneEntity> telefones = clienteEntity.getTelefones();

        TelefoneEntity telefoneEntity = buscarTelefoneExistente(idTelefone, telefones);

    }

    private TelefoneEntity buscarTelefoneExistente(Long idTelefone, List<TelefoneEntity> telefones) {
        return telefones.stream().filter(
                t -> t.getId().equals(idTelefone)).findFirst()
                .orElseThrow(
                        () -> new TelefoneNotFoundException(String.format("Telefone não encontrado para o id %s", idTelefone)));
    }
}

