package br.com.mercadosallas.telefones.repository;

import br.com.mercadosallas.telefones.model.TelefoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TelefoneRepository extends JpaRepository<TelefoneEntity, Long> {
    Optional<TelefoneEntity> findByIdCliente(String idCliente);

    Optional<List<TelefoneEntity>> findAllByIdCliente(String idCliente);

    @Modifying
    @Query(value = "DELETE FROM TELEFONE t WHERE t.id = ?1", nativeQuery = true)
    void deletar(Long idTelefone);

    @Modifying
    @Query(value = "DELETE FROM TELEFONE t WHERE t.id_cliente = ?1", nativeQuery = true)
    void deletarTelefonesDoCliente(String idCliente);

}
