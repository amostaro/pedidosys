package com.totalshake.pedidosys.services;

import com.totalshake.pedidosys.exceptions.PedidoNaoEncontradoException;
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

    public Pedido retrievePedidoById(Long idPedido) throws PedidoNaoEncontradoException {

        Pedido pedido = null;
        try {
            pedido = pedidoRepository.findById(idPedido).orElseThrow(
                    () -> new PedidoNaoEncontradoException("Pedido: "+idPedido+" não encontrado.")
            );
        } catch (Exception e) {
            throw new PedidoNaoEncontradoException("Pedido: "+idPedido+" não encontrado.");
        }
        return pedido;

    }
}
