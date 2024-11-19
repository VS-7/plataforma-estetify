package com.ifcolab.estetify.model.dao;

import com.ifcolab.estetify.factory.DatabaseJPA;
import com.ifcolab.estetify.model.Agenda;
import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.exceptions.AgendaException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
        Agenda a = this.entityManager.find(Agenda.class, id);
        this.entityManager.close();
        return a;
    }
    
    @Override
    public List<Agenda> findAll() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT DISTINCT a FROM Agenda a LEFT JOIN FETCH a.consultas";
        qry = this.entityManager.createQuery(jpql, Agenda.class);
        List<Agenda> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }
    
    public List<Consulta> findConsultasByData(LocalDate data) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT c FROM Consulta c WHERE DATE(c.dataHora) = :data";
        qry = this.entityManager.createQuery(jpql, Consulta.class);
        qry.setParameter("data", data);
        List<Consulta> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }
    
    public List<Consulta> findConsultasByPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT c FROM Consulta c WHERE DATE(c.dataHora) BETWEEN :dataInicio AND :dataFim";
        qry = this.entityManager.createQuery(jpql, Consulta.class);
        qry.setParameter("dataInicio", dataInicio);
        qry.setParameter("dataFim", dataFim);
        List<Consulta> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }
    
    public List<Consulta> findConsultasAgendadasPorData(LocalDate data) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT c FROM Consulta c WHERE DATE(c.dataHora) = :data AND c.status = 'AGENDADA'";
        qry = this.entityManager.createQuery(jpql, Consulta.class);
        qry.setParameter("data", data);
        List<Consulta> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }
    
    public List<Consulta> findConsultasPorProfissionalEData(int profissionalId, boolean isMedico, LocalDate data) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = isMedico ?
            "SELECT c FROM Consulta c WHERE c.medico.id = :profissionalId AND DATE(c.dataHora) = :data" :
            "SELECT c FROM Consulta c WHERE c.enfermeira.id = :profissionalId AND DATE(c.dataHora) = :data";
        qry = this.entityManager.createQuery(jpql, Consulta.class);
        qry.setParameter("profissionalId", profissionalId);
        qry.setParameter("data", data);
        List<Consulta> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }
    
    public List<LocalDateTime> getHorariosDisponiveis(LocalDate data, int medicoId, int enfermeiraId) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        // Consulta para obter horários ocupados
        jpql = "SELECT c.dataHora FROM Consulta c " +
               "WHERE DATE(c.dataHora) = :data " +
               "AND (c.medico.id = :medicoId OR c.enfermeira.id = :enfermeiraId) " +
               "AND c.status != 'CANCELADA'";
        qry = this.entityManager.createQuery(jpql, LocalDateTime.class);
        qry.setParameter("data", data);
        qry.setParameter("medicoId", medicoId);
        qry.setParameter("enfermeiraId", enfermeiraId);
        List<LocalDateTime> horariosOcupados = qry.getResultList();
        this.entityManager.close();

        // Gera lista de horários disponíveis (8h às 18h, intervalos de 1h)
        List<LocalDateTime> todosHorarios = new ArrayList<>();
        LocalDateTime horarioInicial = data.atStartOfDay().plusHours(8);
        LocalDateTime horarioFinal = data.atStartOfDay().plusHours(18);

        while (horarioInicial.isBefore(horarioFinal)) {
            if (!horariosOcupados.contains(horarioInicial)) {
                todosHorarios.add(horarioInicial);
            }
            horarioInicial = horarioInicial.plusHours(1);
        }

        return todosHorarios;
    }
}