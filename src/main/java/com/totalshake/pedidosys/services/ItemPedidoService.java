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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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

        ItemPedido itemPedido = this.retrieveItensPedidoById(idItemPedido);
        itemPedido.setApagadoEm(new Date());
        this.itemPedidoRepository.deleteById(idItemPedido);
    }

//    public ItemPedido createItemPedido(ItemPedidoDTO itemPedidoDTO) throws PedidoNaoEncontradoException {
//
//        if (ObjectUtils.isEmpty(itemPedidoDTO)) {
//            throw new ItensPedidoNaoEncontradoException("Operação inválida! Item Pedido não pode ser vazio.");
//        }
//
//        Long idPedido = itemPedidoDTO.getPedidoDTO().getId();
//        Pedido pedido = pedidoRepository.findById(idPedido).orElseThrow(
//                () -> new PedidoNaoEncontradoException("Operação inválida! Pedido não cadastrado.")
//        );
//
//        itemPedidoDTO.setId(null);
//        itemPedidoDTO.setQuantidadeItens(itemPedidoDTO.getQuantidadeItens());
//        itemPedidoDTO.setDescricaoPedido(itemPedidoDTO.getDescricaoPedido());
//
//        ItemPedido itemPedido = super.convertToModel(itemPedidoDTO, ItemPedido.class);
//        itemPedido.setPedido(pedido);
//
//        this.itemPedidoRepository.save(itemPedido);
//        return itemPedido;
//    }

    public List<ItemPedido> createItemPedido(List<ItemPedidoDTO> itemPedidoListDTO) throws PedidoNaoEncontradoException {

        if (ObjectUtils.isEmpty(itemPedidoListDTO)) {
            throw new ItensPedidoNaoEncontradoException("Operação inválida! Item Pedido não pode ser vazio.");
        }

        List<ItemPedido> itemPedidoList = new ArrayList<>();

        itemPedidoListDTO.forEach(itemPedidoDTO -> {
            Long idPedido = itemPedidoDTO.getPedidoDTO().getId();
            Pedido pedido = pedidoRepository.findById(idPedido).orElseThrow(
                    () -> new PedidoNaoEncontradoException("Operação inválida! Pedido não cadastrado.")
            );

            itemPedidoDTO.setId(null);
            itemPedidoDTO.setQuantidadeItens(itemPedidoDTO.getQuantidadeItens());
            itemPedidoDTO.setDescricaoPedido(itemPedidoDTO.getDescricaoPedido());

            ItemPedido itemPedido = super.convertToModel(itemPedidoDTO, ItemPedido.class);
            itemPedido.setPedido(pedido);

            itemPedidoList.add(itemPedido);

        });

        this.itemPedidoRepository.saveAll(itemPedidoList);

//        for (ItemPedidoDTO itemPedidoDTO : itemPedidoListDTO) {
//            Long idPedido = itemPedidoDTO.getPedidoDTO().getId();
//            Pedido pedido = pedidoRepository.findById(idPedido).orElseThrow(
//                    () -> new PedidoNaoEncontradoException("Operação inválida! Pedido não cadastrado.")
//            );
//
//            itemPedidoDTO.setId(null);
//            itemPedidoDTO.setQuantidadeItens(itemPedidoDTO.getQuantidadeItens());
//            itemPedidoDTO.setDescricaoPedido(itemPedidoDTO.getDescricaoPedido());
//
//            ItemPedido itemPedido = super.convertToModel(itemPedidoDTO, ItemPedido.class);
//            itemPedido.setPedido(pedido);
//            this.itemPedidoRepository.save(itemPedido);
//
//        }

        return Collections.singletonList(super.convertTo(itemPedidoListDTO, ItemPedido.class));
    }

    public ItemPedido updateItensPedido(ItemPedidoDTO itemPedidoDTO) {

        ItemPedido itemPedidoUpdate = null;
        if (!ObjectUtils.isEmpty(itemPedidoDTO)) {
            itemPedidoUpdate = this.retrieveItensPedidoById(itemPedidoDTO.getId());
            itemPedidoUpdate.setQuantidadeItens(itemPedidoDTO.getQuantidadeItens());
            itemPedidoUpdate.setDescricaoPedido(itemPedidoDTO.getDescricaoPedido());
            itemPedidoUpdate.setAtualizadoEm(new Date());

        } else {
            throw new ItensPedidoNaoEncontradoException("Item Pedido: " + itemPedidoDTO.getId()+ " não encontrado.");
        }

        return itemPedidoRepository.save(itemPedidoUpdate);
    }
}
