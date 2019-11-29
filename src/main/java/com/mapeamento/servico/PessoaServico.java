package com.mapeamento.servico;

import com.mapeamento.model.Endereco;
import com.mapeamento.model.Pessoa;
import com.mapeamento.repository.PessoaRepositorio;
import com.mapeamento.util.ResponseUtil;
import spark.Request;
import spark.Response;

import java.util.ArrayList;

public class PessoaServico  {

    private static PessoaRepositorio repositorio = PessoaRepositorio.getInstance();

    public static Object adicionar(Request request, Response response){

        final String nome = request.queryParams("nome");
        final String sobrenome = request.queryParams("sobrenome");
        final String cpf = request.queryParams("cpf");
        final String telefone = request.queryParams("telefone");

        final String logradouro = request.queryParams("logradouro");
        final String numero = request.queryParams("numero");
        final String complemento = request.queryParams("complemento");
        final String cep = request.queryParams("cep");

        if (nome == null || sobrenome == null || cpf == null || logradouro == null
                || numero == null || complemento == null || cep == null){
            response.status(400);
            return ResponseUtil.error("requisição invalida");
        }
        if (repositorio.pesquisaPorCPF(cpf) != null){
            response.status(400);
            return ResponseUtil.error("CPF já cadastrado");
        }

        repositorio.adicionar(new Pessoa(nome, sobrenome, cpf, telefone, new Endereco(logradouro,numero,complemento,cep)));
        return ResponseUtil.success("adicionado");
    }

    public static Object editar(Request request, Response response){

        final String nome = request.queryParams("nome");
        final String sobrenome = request.queryParams("sobrenome");
        final String cpf = request.queryParams("cpf");

        final String logradouro = request.queryParams("logradouro");
        final String numero = request.queryParams("numero");
        final String complemento = request.queryParams("complemento");
        final String cep = request.queryParams("cep");

        if (cpf == null){
            response.status(400);
            return ResponseUtil.error("requisição invalida");
        }

        Pessoa pessoa = repositorio.pesquisaPorCPF(cpf);

        if (pessoa == null){
            response.status(400);
            return ResponseUtil.error("usuário não encontrado");
        }
        if (nome != null){
            pessoa.setNome(nome);
        }
        if (sobrenome != null){
            pessoa.setSobrenome(sobrenome);
        }
        if (logradouro != null){
            pessoa.getEndereco().setLogradouro(logradouro);
        }
        if (numero != null){
            pessoa.getEndereco().setNumero(numero);
        }
        if (complemento != null){
            pessoa.getEndereco().setComplemento(complemento);
        }
        if (cep != null){
            pessoa.getEndereco().setCep(cep);
        }

        repositorio.edit(pessoa);
        return ResponseUtil.success("Editado com sucesso");
    }

    public static Object deletar(Request request, Response response){
        final String cpf = request.queryParams("cpf");

        if (cpf == null){
            response.status(400);
            return ResponseUtil.error("requisição invalida");
        }

        Pessoa pessoa = repositorio.pesquisaPorCPF(cpf);

        if (pessoa == null){
            response.status(400);
            return ResponseUtil.error("usuário não existe");
        }

        repositorio.delete(pessoa);

        return ResponseUtil.success("Deletado com sucesso");

    }

    public static Object verTodos(Request request, Response response){

        return repositorio.verTodos();

    }

    public static Object pesquisarPorNome(Request request, Response response){

        final String nome = request.params("nome");

        if (nome == null){
            response.status(400);
            return ResponseUtil.error("parametros invalidos");
        }

        return repositorio.pesquisaPorNome(nome);

    }

    public static Object pesquisarPorCPF(Request request, Response response){

        final String cpf = request.params("cpf");

        if (cpf == null){
            response.status(400);
            return ResponseUtil.error("parametros invalidos");
        }
        return repositorio.pesquisaPorCPF(cpf);
    }

}
