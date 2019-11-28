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

    @ElementCollection
    @Cascade(CascadeType.DELETE)
    private List<Telefone> telefones;

    @ElementCollection
    @Cascade(CascadeType.DELETE)
    private List<Endereco> enderecos;


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
}

