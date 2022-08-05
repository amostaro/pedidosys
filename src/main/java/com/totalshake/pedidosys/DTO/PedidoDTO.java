package com.totalshake.pedidosys.DTO;

import com.totalshake.pedidosys.enums.EnumStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

    private Long id;
    private LocalDateTime dataHoraPedido;
    private EnumStatus statusPedido;
    private List<ItemPedidoDTO> itensPedidoListDTO;
}
