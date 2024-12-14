package com.ifcolab.estetify.model.dao;

import com.ifcolab.estetify.factory.DatabaseJPA;
import com.ifcolab.estetify.model.Agenda;
import com.ifcolab.estetify.model.HorarioDisponivel;
import com.ifcolab.estetify.model.Medico;
import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.exceptions.AgendaException;
import java.time.LocalDate;
import java.util.List;

public class AgendaDAO extends Dao<Agenda> {
    
    public AgendaDAO() {
    }
    
    @Override
    public boolean delete(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        this.entityManager.getTransaction().begin();
        Agenda agenda = this.entityManager.find(Agenda.class, id);
        if (agenda != null) {
            this.entityManager.remove(agenda);
        } else {
            this.entityManager.getTransaction().rollback();
            throw new AgendaException("Erro - Agenda inexistente.");
        }
        this.entityManager.getTransaction().commit();
        this.entityManager.close();
        return true;
    }
    
    @Override
    public Agenda find(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        Agenda agenda = this.entityManager.find(Agenda.class, id);
        this.entityManager.close();
        return agenda;
    }
    
    @Override
    public List<Agenda> findAll() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT a FROM Agenda a";
        qry = this.entityManager.createQuery(jpql, Agenda.class);
        List<Agenda> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }
    
    public List<Agenda> findByMedico(Medico medico) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT a FROM Agenda a WHERE a.medico = :medico";
        qry = this.entityManager.createQuery(jpql, Agenda.class);
        qry.setParameter("medico", medico);
        List<Agenda> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }
    
    public Agenda findByMedicoAndData(Medico medico, LocalDate data) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT a FROM Agenda a WHERE a.medico = :medico AND a.data = :data";
        qry = this.entityManager.createQuery(jpql, Agenda.class);
        qry.setParameter("medico", medico);
        qry.setParameter("data", data);
        List<Agenda> lst = qry.getResultList();
        this.entityManager.close();
        return !lst.isEmpty() ? lst.get(0) : null;
    }
    
    public List<Agenda> findByData(LocalDate data) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT a FROM Agenda a WHERE a.data = :data";
        qry = this.entityManager.createQuery(jpql, Agenda.class);
        qry.setParameter("data", data);
        List<Agenda> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }
    
    public List<HorarioDisponivel> buscarHorarios(Agenda agenda) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        try {
            jpql = "SELECT h FROM HorarioDisponivel h WHERE h.agenda.id = :agendaId";
            qry = this.entityManager.createQuery(jpql, HorarioDisponivel.class);
            qry.setParameter("agendaId", agenda.getId());
            return qry.getResultList();
        } finally {
            this.entityManager.close();
        }
    }
    
    public List<Consulta> buscarConsultas(Agenda agenda) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        try {
            jpql = "SELECT c FROM Consulta c WHERE c.agenda.id = :agendaId";
            qry = this.entityManager.createQuery(jpql, Consulta.class);
            qry.setParameter("agendaId", agenda.getId());
            return qry.getResultList();
        } finally {
            this.entityManager.close();
        }
    }
    
    public void salvarHorario(HorarioDisponivel horario) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.persist(horario);
            this.entityManager.getTransaction().commit();
        } finally {
            this.entityManager.close();
        }
    }
    
    public void removerHorario(HorarioDisponivel horario) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        try {
            this.entityManager.getTransaction().begin();
            horario = this.entityManager.merge(horario);
            this.entityManager.remove(horario);
            this.entityManager.getTransaction().commit();
        } finally {
            this.entityManager.close();
        }
    }
}
