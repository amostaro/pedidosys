package com.totalshake.pedidosys.services;

import com.totalshake.pedidosys.DTO.PedidoDTO;
import com.totalshake.pedidosys.enums.EnumStatus;
import com.totalshake.pedidosys.exceptions.PedidoNaoEncontradoException;
import com.totalshake.pedidosys.models.Pedido;
import com.totalshake.pedidosys.repositories.PedidoRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PedidoService extends BaseService {

    @Autowired
    PedidoRepository pedidoRepository;
    public List<Pedido> retrieveAllPedidos() {
        List<Pedido> pedidosList = this.pedidoRepository.findAll();
        pedidosList.stream()
                .map(ped -> (super.convertToDTO(ped, PedidoDTO.class)))
                .toList();
        return pedidosList;
    }

    public Pedido retrievePedidoById(Long idPedido) throws PedidoNaoEncontradoException {

        Pedido pedido = null;
        try {
            pedido = this.pedidoRepository.findById(idPedido).orElseThrow(
                    () -> new PedidoNaoEncontradoException("Pedido: "+idPedido+" não encontrado.")
            );
        } catch (Exception e) {
            throw new PedidoNaoEncontradoException("Pedido: "+idPedido+" não encontrado.");
        }
        return pedido;

    }

    public void deletePedidoById(Long idPedido) throws PedidoNaoEncontradoException{

        Pedido pedido = this.retrievePedidoById(idPedido);
        pedido.setApagadoEm(new Date());
        this.pedidoRepository.deleteById(idPedido);
    }

    public Pedido createPedido(PedidoDTO pedidoDTO) throws Exception {

        try {
            pedidoDTO.setDataHoraPedido(LocalDateTime.now());
            pedidoDTO.setStatusPedido(EnumStatus.REALIZADO);
            pedidoDTO.setItensPedidoListDTO(new ArrayList<>());

            return this.pedidoRepository.save(super.convertToModel(pedidoDTO, Pedido.class));
        } catch(Exception e) {
            throw new Exception();
        }
    }

    public Pedido updatePedido(PedidoDTO pedidoDTO) {

        Pedido pedido = null;
        if (!ObjectUtils.isEmpty(pedidoDTO)) {
            pedido = this.retrievePedidoById(pedidoDTO.getId());
            pedido.setStatusPedido(pedidoDTO.getStatusPedido());
            pedido.setAtualizadoEm(new Date());
        } else {
            throw new PedidoNaoEncontradoException("Pedido: "+pedidoDTO.getId() + " não encontrado.");
        }

        return pedidoRepository.save(pedido);
    }

    public void receberPagamentoPedidoById(Long idPedido) throws PedidoNaoEncontradoException {

        Pedido pedido = this.retrievePedidoById(idPedido);

        pedido.setStatusPedido(EnumStatus.PAGO);
        pedido.setAtualizadoEm(new Date());

        this.pedidoRepository.save(pedido);
    }

    public String retornarStatusPedidoById(Long idPedido) {

        Pedido pedido = this.retrievePedidoById(idPedido);
        return pedido.getStatusPedido().getCodigo();
    }
}
