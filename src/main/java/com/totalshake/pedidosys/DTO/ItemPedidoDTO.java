package com.totalshake.pedidosys.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoDTO {

    private Long id;
    private Integer quantidadeItens;
    private String descricaoPedido;
    private PedidoDTO pedidoDTO;
}
