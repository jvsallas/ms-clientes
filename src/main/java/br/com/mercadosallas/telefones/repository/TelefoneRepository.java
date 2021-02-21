package br.com.mercadosallas.telefones.repository;

import br.com.mercadosallas.telefones.model.TelefoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TelefoneRepository extends JpaRepository<TelefoneEntity, Long> {
    Optional<TelefoneEntity> findByIdCliente(String idCliente);

}
