package com.ifcolab.estetify.model.dao;

import com.ifcolab.estetify.factory.DatabaseJPA;
import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.exceptions.ConsultaException;
import java.util.List;
import javax.persistence.TypedQuery;

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
        jpql = "SELECT DISTINCT c FROM Consulta c " +
               "LEFT JOIN FETCH c.procedimentos " +
               "LEFT JOIN FETCH c.paciente " +
               "LEFT JOIN FETCH c.medico " +
               "LEFT JOIN FETCH c.enfermeira";
        TypedQuery<Consulta> query = this.entityManager.createQuery(jpql, Consulta.class);
        List<Consulta> lst = query.getResultList();
        this.entityManager.close();
        return lst;
    }
}
