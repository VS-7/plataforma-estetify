package com.ifcolab.estetify.model.dao;

import com.ifcolab.estetify.factory.DatabaseJPA;
import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.exceptions.ConsultaException;
import java.time.LocalDateTime;
import java.util.List;

public class ConsultaDAO extends Dao<Consulta> {
    
    public ConsultaDAO() {
    }
    
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
        Consulta c = this.entityManager.find(Consulta.class, id);
        this.entityManager.close();
        return c;
    }
    
    @Override
    public List<Consulta> findAll() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT DISTINCT c FROM Consulta c LEFT JOIN FETCH c.procedimentos";
        qry = this.entityManager.createQuery(jpql, Consulta.class);
        List<Consulta> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }
    
    public List<Consulta> findByPaciente(int pacienteId) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT c FROM Consulta c WHERE c.paciente.id = :pacienteId";
        qry = this.entityManager.createQuery(jpql, Consulta.class);
        qry.setParameter("pacienteId", pacienteId);
        List<Consulta> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }
    
    public List<Consulta> findByMedico(int medicoId) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT c FROM Consulta c WHERE c.medico.id = :medicoId";
        qry = this.entityManager.createQuery(jpql, Consulta.class);
        qry.setParameter("medicoId", medicoId);
        List<Consulta> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }
    
    public List<Consulta> findByEnfermeira(int enfermeiraId) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT c FROM Consulta c WHERE c.enfermeira.id = :enfermeiraId";
        qry = this.entityManager.createQuery(jpql, Consulta.class);
        qry.setParameter("enfermeiraId", enfermeiraId);
        List<Consulta> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }
    
    public List<Consulta> findByPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT c FROM Consulta c WHERE c.dataHora BETWEEN :inicio AND :fim";
        qry = this.entityManager.createQuery(jpql, Consulta.class);
        qry.setParameter("inicio", inicio);
        qry.setParameter("fim", fim);
        List<Consulta> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }
    
    public List<Consulta> findByStatus(String status) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT c FROM Consulta c WHERE c.status = :status";
        qry = this.entityManager.createQuery(jpql, Consulta.class);
        qry.setParameter("status", status);
        List<Consulta> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }
    
    public List<Consulta> findByProcedimento(int procedimentoId) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT c FROM Consulta c JOIN c.procedimentos p WHERE p.id = :procedimentoId";
        qry = this.entityManager.createQuery(jpql, Consulta.class);
        qry.setParameter("procedimentoId", procedimentoId);
        List<Consulta> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }
    
    public boolean verificarDisponibilidade(LocalDateTime dataHora, int medicoId, int enfermeiraId) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT COUNT(c) FROM Consulta c " +
               "WHERE c.dataHora = :dataHora " +
               "AND (c.medico.id = :medicoId OR c.enfermeira.id = :enfermeiraId) " +
               "AND c.status != 'CANCELADA'";
        qry = this.entityManager.createQuery(jpql, Long.class);
        qry.setParameter("dataHora", dataHora);
        qry.setParameter("medicoId", medicoId);
        qry.setParameter("enfermeiraId", enfermeiraId);
        Long count = (Long) qry.getSingleResult();
        this.entityManager.close();
        return count == 0;
    }
}
