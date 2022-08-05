package com.totalshake.pedidosys.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ItensPedidoNaoEncontradoException extends RuntimeException {
    public ItensPedidoNaoEncontradoException(String message) {
        super(message);
    }
}
