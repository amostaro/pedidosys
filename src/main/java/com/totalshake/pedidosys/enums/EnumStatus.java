package com.totalshake.pedidosys.enums;

import lombok.Getter;

public enum EnumStatus {

    REALIZADO ("REALIZADO", "REALIZADO"),
    CANCELADO ("CANCELADO", "CANCELADO"),
    PAGO ("PAGO", "PAGO"),
    NAO_AUTORIZADO ("NAO_AUTORIZADO", "NAO_AUTORIZADO"),
    CONFIRMADO ("CONFIRMADO", "CONFIRMADO"),
    PRONTO ("PRONTO", "PRONTO"),
    SAIU_PARA_ENTREGA ("SAIU_PARA_ENTREGA", "SAIU_PARA_ENTREGA"),
    ENTREGUE ("ENTREGUE", "ENTREGUE");

    @Getter
    private String codigo;

    @Getter
    private String descricao;

    private EnumStatus(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

}
