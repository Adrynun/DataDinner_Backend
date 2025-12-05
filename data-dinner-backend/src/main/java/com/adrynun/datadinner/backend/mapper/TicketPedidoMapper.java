package com.adrynun.datadinner.backend.mapper;

import com.adrynun.datadinner.backend.dto.TicketPedidoDTO;
import com.adrynun.datadinner.backend.entity.TicketPedido;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper para convertir entre TicketPedido y TicketPedidoDTO.
 */
@Mapper(componentModel = "spring")
public interface TicketPedidoMapper {

    TicketPedidoMapper INSTANCE = Mappers.getMapper(TicketPedidoMapper.class);

    TicketPedidoDTO toDTO(TicketPedido ticket);

    TicketPedido toEntity(TicketPedidoDTO dto);
}
