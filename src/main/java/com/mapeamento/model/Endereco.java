package com.mapeamento.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Endereco implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private String logradouro;
    private String numero;
    private String complemento;
    private String cep;

    public Endereco(String logradouro, String numero, String complemento, String cep) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
    }

    public Endereco() {
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
