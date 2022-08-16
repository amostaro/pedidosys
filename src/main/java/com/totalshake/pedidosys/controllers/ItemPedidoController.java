package com.totalshake.pedidosys.controllers;

import com.totalshake.pedidosys.DTO.ItemPedidoDTO;
import com.totalshake.pedidosys.exceptions.ItensPedidoNaoEncontradoException;
import com.totalshake.pedidosys.models.ItemPedido;
import com.totalshake.pedidosys.services.ItemPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/itens-pedido")
public class ItemPedidoController extends BaseController {

    @Autowired
    ItemPedidoService itemPedidoService;

    @GetMapping("/retrieve-all")
    public ResponseEntity<List<ItemPedido>> retrieveAllItensPedido() {
        List<ItemPedido> itensPedidoList = itemPedidoService.retrieveAllItensPedido();
        return ResponseEntity.ok(itensPedidoList);
    }

    @GetMapping("/retrieve/{id}")
    public ResponseEntity<ItemPedido> retrieveItensPedidoById(@Valid @PathVariable("id") Long idItensPedido) throws ItensPedidoNaoEncontradoException {
        ItemPedido itemPedido = itemPedidoService.retrieveItensPedidoById(idItensPedido);
        return ResponseEntity.ok(itemPedido);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ItemPedido> deleteItensPedidoById(@Valid @PathVariable("id") Long idItensPedido) throws ItensPedidoNaoEncontradoException {
        itemPedidoService.deleteItensPedidoById(idItensPedido);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ItemPedido> createItemPedido(@Valid @RequestBody ItemPedidoDTO itemPedidoDTO) throws Exception {
        ItemPedido novoItemPedido = itemPedidoService.createItemPedido(itemPedidoDTO);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoItemPedido.getId()).toUri();
        return ResponseEntity.created(location).body(novoItemPedido);
    }

//    @PostMapping("/create")
//    public ResponseEntity<List<ItemPedido>> createItemPedido(@Valid @RequestBody ItemPedidoDTO itemPedidoDTO) throws Exception {
//        List<ItemPedido> novoItemPedidoList = itemPedidoService.createItemPedido(itemPedidoDTO);
//
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoItemPedidoList).toUri();
//        return ResponseEntity.created(location).body(novoItemPedidoList);
//    }

    @PutMapping("/update")
    public ResponseEntity<ItemPedido> updateItensPedido(@Valid @RequestBody ItemPedidoDTO itemPedidoDTO) throws ItensPedidoNaoEncontradoException {
        ItemPedido itemPedido = itemPedidoService.updateItensPedido(itemPedidoDTO);
        return ResponseEntity.ok(itemPedido);
    }
}
