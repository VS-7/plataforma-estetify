package com.ifcolab.estetify.model.dao;

import com.ifcolab.estetify.factory.DatabaseJPA;
import com.ifcolab.estetify.model.Relatorio;
import com.ifcolab.estetify.model.exceptions.RelatorioException;

import java.util.List;
import javax.persistence.TypedQuery;

public class RelatorioDAO extends Dao<Relatorio> {
    
    @Override
    public boolean delete(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        this.entityManager.getTransaction().begin();
        Relatorio relatorio = this.entityManager.find(Relatorio.class, id);
        if (relatorio != null) {
            this.entityManager.remove(relatorio);
        } else {
            this.entityManager.getTransaction().rollback();
            throw new RelatorioException("Erro - Relatório inexistente.");
        }
        this.entityManager.getTransaction().commit();
        this.entityManager.close();
        return true;
    }
    
    @Override
    public Relatorio find(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        Relatorio relatorio = this.entityManager.find(Relatorio.class, id);
        this.entityManager.close();
        return relatorio;
    }
    
    @Override
    public List<Relatorio> findAll() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT DISTINCT r FROM Relatorio r LEFT JOIN FETCH r.consulta";
        TypedQuery<Relatorio> query = this.entityManager.createQuery(jpql, Relatorio.class);
        List<Relatorio> lst = query.getResultList();
        this.entityManager.close();
        return lst;
    }
}