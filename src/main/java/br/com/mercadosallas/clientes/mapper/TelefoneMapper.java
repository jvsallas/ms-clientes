package br.com.mercadosallas.clientes.mapper;

import br.com.mercadosallas.clientes.dto.TelefoneDto;
import br.com.mercadosallas.clientes.dto.TelefoneForm;
import br.com.mercadosallas.clientes.model.TelefoneEntity;

import java.util.List;
import java.util.stream.Collectors;

public class TelefoneMapper {

    public static TelefoneEntity mapToEntity(TelefoneForm telefoneForm){

        TelefoneEntity telefoneEntity = new TelefoneEntity();
        telefoneEntity.setDdd(telefoneForm.getDdd());
        telefoneEntity.setNumero(telefoneForm.getNumero());
        telefoneEntity.setTipo(telefoneForm.getTipo());

        return telefoneEntity;
    }


    public static TelefoneDto mapToDto(TelefoneEntity telefoneEntity){

        TelefoneDto telefoneDto = new TelefoneDto();
        telefoneDto.setId(telefoneEntity.getId());
        telefoneDto.setDdd(telefoneEntity.getDdd());
        telefoneDto.setNumero(telefoneEntity.getNumero());
        telefoneDto.setTipo(telefoneEntity.getTipo());

        return telefoneDto;
    }

    public static List<TelefoneEntity> mapToListEntity(List<TelefoneForm> telefones){
        return telefones.stream().map(TelefoneMapper::mapToEntity).collect(Collectors.toList());
    }

    public static List<TelefoneDto> mapToListDto(List<TelefoneEntity> telefones){
        return telefones.stream().map(TelefoneMapper::mapToDto).collect(Collectors.toList());
    }

}
