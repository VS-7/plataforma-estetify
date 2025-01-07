package com.ifcolab.estetify.model.dao;

import com.ifcolab.estetify.factory.DatabaseJPA;
import com.ifcolab.estetify.model.Medico;
import java.util.List;

public class MedicoDAO extends Dao<Medico> {
    
    public MedicoDAO() {
    }
    
    @Override
    public boolean delete(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        this.entityManager.getTransaction().begin();
        
        Medico medico = this.entityManager.find(Medico.class, id);
        if (medico == null) {
            this.entityManager.getTransaction().rollback();
            return false;
        }
        
        this.entityManager.remove(medico);
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
    
    public Medico findByCPF(String cpf) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT p FROM Medico p WHERE p.cpf = :cpf";
        qry = this.entityManager.createQuery(jpql, Medico.class);
        qry.setParameter("cpf", cpf);
        List<Medico> lst = qry.getResultList();
        this.entityManager.close();
        return !lst.isEmpty() ? lst.get(0) : null;
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
