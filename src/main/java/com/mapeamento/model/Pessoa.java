package com.mapeamento.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import javax.persistence.*;
import java.util.List;

@Entity
public class Pessoa {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private String nome;
    private String sobrenome;

    @Column(unique = true)
    private String cpf;

    private String telefone;

    @OneToOne
    private Endereco enderecos;


    public Pessoa(String nome, String sobrenome, String cpf, String telefone, Endereco enderecos) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.enderecos = enderecos;
    }

    public Pessoa() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone() {
        return telefone;
    }

    public Endereco getEndereco() {
        return enderecos;
    }
}

