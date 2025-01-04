package com.ifcolab.estetify.model.dao;

import com.ifcolab.estetify.factory.DatabaseJPA;
import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.exceptions.ConsultaException;
import java.util.List;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.time.LocalDate;

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
    
    public List<Consulta> buscarConsultasNoPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        try {
            jpql = "SELECT c FROM Consulta c WHERE c.dataHora BETWEEN :inicio AND :fim";
            qry = this.entityManager.createQuery(jpql, Consulta.class);
            qry.setParameter("inicio", inicio);
            qry.setParameter("fim", fim);
            return qry.getResultList();
        } finally {
            this.entityManager.close();
        }
    }
    
    public List<Consulta> buscarPorData(LocalDate data) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        try {
            // Criar LocalDateTime para início e fim do dia
            LocalDateTime inicioDia = data.atStartOfDay();
            LocalDateTime fimDia = data.atTime(23, 59, 59);
            
            jpql = "SELECT DISTINCT c FROM Consulta c " +
                   "LEFT JOIN FETCH c.procedimentos " +
                   "LEFT JOIN FETCH c.paciente " +
                   "LEFT JOIN FETCH c.medico " +
                   "LEFT JOIN FETCH c.enfermeira " +
                   "WHERE c.dataHora BETWEEN :inicio AND :fim " +
                   "ORDER BY c.dataHora";
            
            TypedQuery<Consulta> query = this.entityManager.createQuery(jpql, Consulta.class);
            query.setParameter("inicio", inicioDia);
            query.setParameter("fim", fimDia);
            
            return query.getResultList();
        } finally {
            this.entityManager.close();
        }
    }
}
