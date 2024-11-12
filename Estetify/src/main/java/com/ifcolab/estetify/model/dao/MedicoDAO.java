package com.ifcolab.estetify.model.dao;

import com.ifcolab.estetify.factory.DatabaseJPA;
import com.ifcolab.estetify.model.Medico;
import com.ifcolab.estetify.model.exceptions.MedicoException;
import java.util.List;

public class MedicoDAO extends Dao<Medico> {
    
    public MedicoDAO() {
    }
    
    @Override
    public boolean delete(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        this.entityManager.getTransaction().begin();
        Medico medico = this.entityManager.find(Medico.class, id);
        if (medico != null) {
            this.entityManager.remove(medico);
        } else {
            this.entityManager.getTransaction().rollback();
            throw new MedicoException("Erro - Médico inexistente.");
        }
        this.entityManager.getTransaction().commit();
        this.entityManager.close();
        return true;
    }
    
    @Override
    public Medico find(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        Medico m = this.entityManager.find(Medico.class, id);
        this.entityManager.close();
        return m;
    }
    
    @Override
    public List<Medico> findAll() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT m FROM Medico m";
        qry = this.entityManager.createQuery(jpql, Medico.class);
        List<Medico> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }
    
    public List<Medico> filterByName(String nome) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT m FROM Medico m WHERE m.nome LIKE :nome";
        qry = this.entityManager.createQuery(jpql, Medico.class);
        qry.setParameter("nome", nome + "%");
        List<Medico> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }
    
    public Medico findByCRM(String crm) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT m FROM Medico m WHERE m.crm = :crm";
        qry = this.entityManager.createQuery(jpql, Medico.class);
        qry.setParameter("crm", crm);
        List<Medico> lst = qry.getResultList();
        this.entityManager.close();
        return !lst.isEmpty() ? lst.get(0) : null;
    }
    
    public Medico findByEmail(String email) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT m FROM Medico m WHERE m.email = :email";
        qry = this.entityManager.createQuery(jpql, Medico.class);
        qry.setParameter("email", email);
        List<Medico> lst = qry.getResultList();
        this.entityManager.close();
        return !lst.isEmpty() ? lst.get(0) : null;
    }
}
