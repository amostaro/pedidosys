package com.totalshake.pedidosys.services;

import com.totalshake.pedidosys.DTO.ItemPedidoDTO;
import com.totalshake.pedidosys.exceptions.ItensPedidoNaoEncontradoException;
import com.totalshake.pedidosys.exceptions.PedidoNaoEncontradoException;
import com.totalshake.pedidosys.models.ItemPedido;
import com.totalshake.pedidosys.repositories.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemPedidoService extends BaseService {

    @Autowired
    ItemPedidoRepository itemPedidoRepository;

    public List<ItemPedido> retrieveAllItensPedido() {
        List<ItemPedido> itensPedidoList = itemPedidoRepository.findAll();
        return itensPedidoList;
    }

    public ItemPedido retrieveItensPedidoById(Long idItemPedido) throws ItensPedidoNaoEncontradoException {

        ItemPedido pedido = null;
        try {
            pedido = itemPedidoRepository.findById(idItemPedido).orElseThrow(
                    () -> new PedidoNaoEncontradoException("Item Pedido: "+idItemPedido+" não encontrado.")
            );
        } catch (Exception e) {
            throw new PedidoNaoEncontradoException("Item Pedido: "+idItemPedido+" não encontrado.");
        }
        return pedido;

    }

    public void deleteItensPedidoById(Long idItemPedido) throws ItensPedidoNaoEncontradoException {

        this.retrieveItensPedidoById(idItemPedido);
        itemPedidoRepository.deleteById(idItemPedido);
    }

    public ItemPedido createItemPedido(ItemPedidoDTO itemPedidoDTO) throws Exception {

        try {

            return itemPedidoRepository.save(super.convertToModel(itemPedidoDTO, ItemPedido.class));
        } catch(Exception e) {
            throw new Exception();
        }
    }
}
