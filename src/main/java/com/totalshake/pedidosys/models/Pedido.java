package com.totalshake.pedidosys.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.totalshake.pedidosys.commons.BaseEntity;
import com.totalshake.pedidosys.enums.EnumStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedidos")
public class Pedido extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "data_hora_pedido")
    private LocalDateTime dataHoraPedido;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pedido")
    private EnumStatus statusPedido;

    @JsonBackReference
    @OneToMany(mappedBy="pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itensPedidoList;
}
