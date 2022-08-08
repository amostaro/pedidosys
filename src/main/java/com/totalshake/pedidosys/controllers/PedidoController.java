package com.totalshake.pedidosys.controllers;

import com.totalshake.pedidosys.DTO.PedidoDTO;
import com.totalshake.pedidosys.exceptions.PedidoNaoEncontradoException;
import com.totalshake.pedidosys.models.Pedido;
import com.totalshake.pedidosys.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController extends BaseController {

    @Autowired
    PedidoService pedidoService;

    @GetMapping("/retrieve-all")
    public ResponseEntity<List<Pedido>> retrieveAllPedidos() {
        List<Pedido> pedidosList = pedidoService.retrieveAllPedidos();
        return ResponseEntity.ok(pedidosList);
    }

    @GetMapping("/retrieve/{id}")
    public ResponseEntity<Pedido> retrievePedidoById(@Valid @PathVariable("id") Long idPedido) throws PedidoNaoEncontradoException {
        Pedido pedido = pedidoService.retrievePedidoById(idPedido);
        return ResponseEntity.ok(pedido);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Pedido> deletePedidoById(@Valid @PathVariable("id") Long idPedido) throws PedidoNaoEncontradoException {
        pedidoService.deletePedidoById(idPedido);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Pedido> createPedido(@Valid @RequestBody PedidoDTO pedidoDTO) throws Exception {
        Pedido novoPedido = pedidoService.createPedido(pedidoDTO);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoPedido.getId()).toUri();
        return ResponseEntity.created(location).body(novoPedido);
    }

    @PutMapping("/update")
    public ResponseEntity<Pedido> updatePedido(@Valid @RequestBody PedidoDTO pedidoDTO) throws PedidoNaoEncontradoException {
        Pedido pedido = pedidoService.updatePedido(pedidoDTO);
        return ResponseEntity.ok(pedido);
    }
}
