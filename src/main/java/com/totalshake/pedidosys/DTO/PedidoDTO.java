package com.totalshake.pedidosys.DTO;

import com.totalshake.pedidosys.enums.EnumStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

    private Long id;
    private LocalDateTime dataHoraPedido;

    @Enumerated(EnumType.STRING)
    private EnumStatus statusPedido;

    private List<ItemPedidoDTO> itensPedidoListDTO;
}
