package com.ifcolab.estetify.model.dao;

import com.ifcolab.estetify.factory.DatabaseJPA;
import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.exceptions.ConsultaException;
import java.util.List;

public class ConsultaDAO extends Dao<Consulta> {
    
    @Override
    public boolean delete(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        this.entityManager.getTransaction().begin();
        Consulta consulta = this.entityManager.find(Consulta.class, id);
        if (consulta != null) {
            this.entityManager.remove(consulta);
        } else {
            this.entityManager.getTransaction().rollback();
            throw new ConsultaException("Erro - Consulta inexistente.");
        }
        this.entityManager.getTransaction().commit();
        this.entityManager.close();
        return true;
    }
    
    @Override
    public Consulta find(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        Consulta consulta = this.entityManager.find(Consulta.class, id);
        this.entityManager.close();
        return consulta;
    }
    
    @Override
    public List<Consulta> findAll() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT c FROM Consulta c";
        qry = this.entityManager.createQuery(jpql, Consulta.class);
        List<Consulta> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }
}
