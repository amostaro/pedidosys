package com.totalshake.pedidosys.controllers;

import com.totalshake.pedidosys.models.Pedido;
import com.totalshake.pedidosys.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
