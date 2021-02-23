package br.com.mercadosallas.telefones.service;

import br.com.mercadosallas.clientes.model.ClienteEntity;
import br.com.mercadosallas.clientes.service.ClienteService;
import br.com.mercadosallas.telefones.dto.TelefoneAtualizacaoForm;
import br.com.mercadosallas.telefones.dto.TelefoneDto;
import br.com.mercadosallas.telefones.dto.TelefoneForm;
import br.com.mercadosallas.telefones.exception.MaximoTelefoneException;
import br.com.mercadosallas.telefones.exception.MinimoTelefoneException;
import br.com.mercadosallas.telefones.exception.TelefoneNotFoundException;
import br.com.mercadosallas.telefones.mapper.TelefoneMapper;
import br.com.mercadosallas.telefones.model.TelefoneEntity;
import br.com.mercadosallas.telefones.repository.TelefoneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.mercadosallas.utils.StringUtils.isNotNullOrBlank;

@Service
public class TelefoneService {

    @Autowired
    private TelefoneRepository telefoneRepository;

    @Autowired
    private ClienteService clienteService;

    private static final Logger log = LoggerFactory.getLogger(TelefoneService.class);


    public TelefoneDto adicionarTelefoneAoCliente(String idCliente, TelefoneForm form) {

        log.info("Adicionando telefone ao cliente: {}", idCliente);

        ClienteEntity clienteEntity = clienteService.buscarClientePorId(idCliente);

        if (clienteEntity.getTelefones().size() == 5)
            throw new MaximoTelefoneException("Não é possível adicionar mais que 5 telefones por cliente.");

        TelefoneEntity telefoneEntity = TelefoneMapper.mapToEntity(form);
        telefoneEntity.setIdCliente(idCliente);

        TelefoneEntity telefone = telefoneRepository.save(telefoneEntity);

        log.info("Telefone adicionado com sucesso.");

        return TelefoneMapper.mapToDto(telefone);

    }

    public List<TelefoneDto> listarTelefonesDoCliente(String idCliente) {

        log.info("Listando todos telefones cadastrados do cliente.");

        ClienteEntity clienteEntity = clienteService.buscarClientePorId(idCliente);

        if (clienteEntity.getTelefones().isEmpty())
            throw new TelefoneNotFoundException("Nenhum telefone cadastrado.");

        return TelefoneMapper.mapToListDto(clienteEntity.getTelefones());
    }

    public TelefoneDto listarTelefonePorId(String idCliente, Long idTelefone) {

        log.info("Listando telefone do cliente pelo id.");

        ClienteEntity clienteEntity = clienteService.buscarClientePorId(idCliente);

        TelefoneEntity telefoneEntity = filtrarTelefonesPorId(idTelefone, clienteEntity.getTelefones());

        return TelefoneMapper.mapToDto(telefoneEntity);
    }

    public TelefoneDto alterarTelefone(String idCliente, Long idTelefone, TelefoneAtualizacaoForm form) {

        log.info("Alterando telefone do cliente.");

        ClienteEntity clienteEntity = clienteService.buscarClientePorId(idCliente);

        TelefoneEntity telefoneEntity = filtrarTelefonesPorId(idTelefone, clienteEntity.getTelefones());

        if (isNotNullOrBlank(form.getDdd()))
            telefoneEntity.setDdd(form.getDdd());

        if (isNotNullOrBlank(form.getNumero()))
            telefoneEntity.setNumero(form.getNumero());

        if (isNotNullOrBlank(form.getTipo()))
            telefoneEntity.setTipo(form.getTipo());

        log.info("Telefone alterado com sucesso.");

        return TelefoneMapper.mapToDto(telefoneEntity);
    }

    public void deletarTelefone(String idCliente, Long idTelefone) {

        log.info("Deletando telefone {} do cliente {}", idCliente, idTelefone);

        ClienteEntity clienteEntity = clienteService.buscarClientePorId(idCliente);

        TelefoneEntity telefoneEntity = filtrarTelefonesPorId(idTelefone, clienteEntity.getTelefones());

        if (clienteEntity.getTelefones().size() == 1)
            throw new MinimoTelefoneException("Não é possível deletar telefone. É necessário ter um ou mais telefones cadastrados.");

        telefoneRepository.delete(telefoneEntity.getId());

        log.info("Telefone deltado com sucesso.");

    }

    private TelefoneEntity filtrarTelefonesPorId(Long idTelefone, List<TelefoneEntity> telefones) {
        return telefones.stream().filter(
                t -> t.getId().equals(idTelefone)).findFirst()
                .orElseThrow(
                        () -> new TelefoneNotFoundException(String.format("Telefone não encontrado para o id %s", idTelefone)));
    }

}

