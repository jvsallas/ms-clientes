package br.com.mercadosallas.clientes.repository;

import br.com.mercadosallas.clientes.model.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<ClienteEntity, String> {
    Optional<ClienteEntity> findByCpf(String cpf);
}
