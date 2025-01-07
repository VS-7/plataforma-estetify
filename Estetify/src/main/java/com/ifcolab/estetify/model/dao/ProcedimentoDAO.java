package com.ifcolab.estetify.model.dao;

import com.ifcolab.estetify.factory.DatabaseJPA;
import com.ifcolab.estetify.model.Procedimento;
import java.time.LocalDateTime;
import java.util.List;

public class ProcedimentoDAO extends Dao<Procedimento> {
    
    public ProcedimentoDAO() {
    }
    
    @Override
    public boolean delete(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        this.entityManager.getTransaction().begin();
        
        Procedimento procedimento = this.entityManager.find(Procedimento.class, id);
        if (procedimento == null) {
            this.entityManager.getTransaction().rollback();
            return false;
        }
        
        this.entityManager.remove(procedimento);
        this.entityManager.getTransaction().commit();
        this.entityManager.close();
        return true;
    }
    
    @Override
    public Procedimento find(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        Procedimento p = this.entityManager.find(Procedimento.class, id);
        this.entityManager.close();
        return p;
    }
    
    @Override
    public List<Procedimento> findAll() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT p FROM Procedimento p";
        qry = this.entityManager.createQuery(jpql, Procedimento.class);
        List<Procedimento> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }
    
    public List<Procedimento> filterByDescricao(String descricao) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT p FROM Procedimento p WHERE p.descricao LIKE :descricao";
        qry = this.entityManager.createQuery(jpql, Procedimento.class);
        qry.setParameter("descricao", "%" + descricao + "%");
        List<Procedimento> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }
    
    public List<Procedimento> findByPaciente(int pacienteId) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT p FROM Procedimento p WHERE p.paciente.id = :pacienteId";
        qry = this.entityManager.createQuery(jpql, Procedimento.class);
        qry.setParameter("pacienteId", pacienteId);
        List<Procedimento> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }
    
    public List<Procedimento> findByEnfermeira(int enfermeiraId) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT p FROM Procedimento p WHERE p.enfermeira.id = :enfermeiraId";
        qry = this.entityManager.createQuery(jpql, Procedimento.class);
        qry.setParameter("enfermeiraId", enfermeiraId);
        List<Procedimento> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }
    
    public List<Procedimento> findByPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT p FROM Procedimento p WHERE p.dataHora BETWEEN :inicio AND :fim";
        qry = this.entityManager.createQuery(jpql, Procedimento.class);
        qry.setParameter("inicio", inicio);
        qry.setParameter("fim", fim);
        List<Procedimento> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }
    
    public List<Procedimento> findByMedico(int medicoId) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT p FROM Procedimento p WHERE p.medico.id = :medicoId";
        qry = this.entityManager.createQuery(jpql, Procedimento.class);
        qry.setParameter("medicoId", medicoId);
        List<Procedimento> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }
    
    public List<Procedimento> findByEquipe(int medicoId, int enfermeiraId) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT p FROM Procedimento p WHERE p.medico.id = :medicoId AND p.enfermeira.id = :enfermeiraId";
        qry = this.entityManager.createQuery(jpql, Procedimento.class);
        qry.setParameter("medicoId", medicoId);
        qry.setParameter("enfermeiraId", enfermeiraId);
        List<Procedimento> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }
    
    public List<Procedimento> findByPacienteEPeriodo(int pacienteId, LocalDateTime inicio, LocalDateTime fim) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT p FROM Procedimento p WHERE p.paciente.id = :pacienteId AND p.dataHora BETWEEN :inicio AND :fim";
        qry = this.entityManager.createQuery(jpql, Procedimento.class);
        qry.setParameter("pacienteId", pacienteId);
        qry.setParameter("inicio", inicio);
        qry.setParameter("fim", fim);
        List<Procedimento> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }
}
