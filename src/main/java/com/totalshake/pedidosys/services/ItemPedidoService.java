package com.totalshake.pedidosys.services;

import com.totalshake.pedidosys.DTO.ItemPedidoDTO;
import com.totalshake.pedidosys.exceptions.ItensPedidoNaoEncontradoException;
import com.totalshake.pedidosys.exceptions.PedidoNaoEncontradoException;
import com.totalshake.pedidosys.models.ItemPedido;
import com.totalshake.pedidosys.models.Pedido;
import com.totalshake.pedidosys.repositories.ItemPedidoRepository;
import com.totalshake.pedidosys.repositories.PedidoRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemPedidoService extends BaseService {

    @Autowired
    ItemPedidoRepository itemPedidoRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    public List<ItemPedido> retrieveAllItensPedido() {
        List<ItemPedido> itensPedidoList = this.itemPedidoRepository.findAll();
        itensPedidoList.stream()
                .map(p -> super.convertToDTO(p, ItemPedidoDTO.class))
                .toList();
        return itensPedidoList;
    }

    public ItemPedido retrieveItensPedidoById(Long idItemPedido) throws ItensPedidoNaoEncontradoException {

        ItemPedido itemPedido = null;
        try {
            itemPedido = this.itemPedidoRepository.findById(idItemPedido).orElseThrow(
                    () -> new ItensPedidoNaoEncontradoException("Item Pedido: "+idItemPedido+" não encontrado.")
            );
        } catch (Exception e) {
            throw new ItensPedidoNaoEncontradoException("Item Pedido: "+idItemPedido+" não encontrado.");
        }
        return itemPedido;

    }

    public void deleteItensPedidoById(Long idItemPedido) throws ItensPedidoNaoEncontradoException {

        this.retrieveItensPedidoById(idItemPedido);
        this.itemPedidoRepository.deleteById(idItemPedido);
    }

    public ItemPedido createItemPedido(ItemPedidoDTO itemPedidoDTO) throws PedidoNaoEncontradoException {

        if (ObjectUtils.isEmpty(itemPedidoDTO)) {
            throw new ItensPedidoNaoEncontradoException("Operação inválida! Item Pedido não pode ser vazio.");
        }

        Long idPedido = itemPedidoDTO.getPedidoDTO().getId();
        Pedido pedido = pedidoRepository.findById(idPedido).orElseThrow(
                () -> new PedidoNaoEncontradoException("Operação inválida! Pedido não cadastrado.")
        );

//        if (pedido == null || ObjectUtils.isEmpty(pedido)) {
//            throw new PedidoNaoEncontradoException("Operação inválida! Pedido não cadastrado.");
//        }

        itemPedidoDTO.setId(null);
        itemPedidoDTO.setQuantidadeItens(itemPedidoDTO.getQuantidadeItens());
        itemPedidoDTO.setDescricaoPedido(itemPedidoDTO.getDescricaoPedido());

        ItemPedido itemPedido = super.convertToModel(itemPedidoDTO, ItemPedido.class);
        itemPedido.setPedido(pedido);



        this.itemPedidoRepository.save(itemPedido);
        return itemPedido;
    }
}
