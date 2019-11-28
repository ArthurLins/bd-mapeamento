package com.mapeamento.model;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class Endereco implements Serializable {

    private String logradouro;
    private String numero;
    private String complemento;
    private String cep;
}
