package com.mapeamento;

import com.google.gson.Gson;
import com.mapeamento.model.Pessoa;
import com.mapeamento.repository.DB;
import com.mapeamento.servico.PessoaServico;
import com.mapeamento.util.ResponseUtil;

import static spark.Spark.*;


public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();
        DB.init();


        before((request, response) -> response.type("application/json"));
        internalServerError((req, res) -> gson.toJson(ResponseUtil.error("500")));
        notFound((req, res) -> gson.toJson(ResponseUtil.error("404")));

        get("pessoa", PessoaServico::verTodos, gson::toJson);
        post("pessoa", PessoaServico::adicionar,gson::toJson);
        get("pessoa/buscar/nome/:nome", PessoaServico::pesquisarPorNome, gson::toJson);
        get("pessoa/buscar/cpf/:cpf", PessoaServico::pesquisarPorCPF, gson::toJson);
        put("pessoa/:cpf", PessoaServico::editar, gson::toJson);
        delete("pessoa/:cpf", PessoaServico::deletar,gson::toJson);

    }
}
