package com.mapeamento.model;

import javax.persistence.Embeddable;

@Embeddable
public class Telefone {
    private int ddd;
    private String numero;
}
