package com.totalshake.pedidosys.services;

import com.totalshake.pedidosys.models.Pedido;
import com.totalshake.pedidosys.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService extends BaseService {

    @Autowired
    PedidoRepository pedidoRepository;
    public List<Pedido> retrieveAllPedidos() {
        List<Pedido> pedidosList = pedidoRepository.findAll();
        return pedidosList;
    }
}
