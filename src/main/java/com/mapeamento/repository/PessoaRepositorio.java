package com.mapeamento.repository;

import com.mapeamento.model.Pessoa;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class PessoaRepositorio {
    private static PessoaRepositorio ourInstance = new PessoaRepositorio();

    public static PessoaRepositorio getInstance() {
        return ourInstance;
    }

    private PessoaRepositorio() {

    }


    public void adicionar(Pessoa pessoa) {
        Session session = DB.getSession();
        try{
            session.beginTransaction();
            session.save(pessoa);
            session.getTransaction().commit();
        } catch (Exception e){
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public void edit(Pessoa pessoa) {
        Session session = DB.getSession();
        try{
            session.beginTransaction();
            session.update(pessoa);
            session.getTransaction().commit();
        } catch (Exception e){
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public void delete(Pessoa pessoa){

        Session session = DB.getSession();
        try{
            session.beginTransaction();
            session.delete(pessoa);
            session.getTransaction().commit();
        } catch (Exception e){
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public List<Pessoa> pesquisaPorNome(String nome){
        Session session = DB.getSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Pessoa> pQuery = criteriaBuilder.createQuery(Pessoa.class);
        Root<Pessoa> root = pQuery.from(Pessoa.class);
        pQuery.select(root).where(criteriaBuilder.like(root.get("nome"), "%"+nome+"%"));
        return session.createQuery(pQuery).list();
    }

    public Pessoa pesquisaPorCPF(String cpf){
        Session session = DB.getSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Pessoa> pQuery = criteriaBuilder.createQuery(Pessoa.class);
        Root<Pessoa> root = pQuery.from(Pessoa.class);
        pQuery.select(root).where(criteriaBuilder.equal(root.get("cpf"), cpf));
        return session.createQuery(pQuery).uniqueResult();
    }

    public List<Pessoa> verTodos(){
        Session session = DB.getSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Pessoa> pQuery = criteriaBuilder.createQuery(Pessoa.class);
        Root<Pessoa> root = pQuery.from(Pessoa.class);
        pQuery.select(root);
        return session.createQuery(pQuery).list();
    }

}
