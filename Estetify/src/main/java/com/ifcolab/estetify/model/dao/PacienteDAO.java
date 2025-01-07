package com.ifcolab.estetify.model.dao;

import com.ifcolab.estetify.factory.DatabaseJPA;
import com.ifcolab.estetify.model.Paciente;
import java.util.List;

public class PacienteDAO extends Dao<Paciente> {
    
    public PacienteDAO(){
    }
    
    @Override
    public boolean delete(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        this.entityManager.getTransaction().begin();
        
        Paciente paciente = this.entityManager.find(Paciente.class, id);
        if (paciente == null) {
            this.entityManager.getTransaction().rollback();
            return false;
        }
        
        this.entityManager.remove(paciente);
        this.entityManager.getTransaction().commit();
        this.entityManager.close();
        return true;
    }
    
    @Override
    public Paciente find(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        Paciente p = this.entityManager.find(Paciente.class, id);
        this.entityManager.close();
        return p;
    }
    
    @Override
    public List<Paciente> findAll() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT p FROM Paciente p";
        qry = this.entityManager.createQuery(jpql, Paciente.class);
        List<Paciente> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }
    
    public List<Paciente> filterByName(String nome) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT p FROM Paciente p WHERE p.nome LIKE :nome";
        qry = this.entityManager.createQuery(jpql, Paciente.class);
        qry.setParameter("nome", nome + "%");
        List<Paciente> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }
    
    public Paciente findByCPF(String cpf) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT p FROM Paciente p WHERE p.cpf = :cpf";
        qry = this.entityManager.createQuery(jpql, Paciente.class);
        qry.setParameter("cpf", cpf);
        List<Paciente> lst = qry.getResultList();
        this.entityManager.close();
        return !lst.isEmpty() ? lst.get(0) : null;
    }
    
    public Paciente findByEmail(String email) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT p FROM Paciente p WHERE p.email = :email";
        qry = this.entityManager.createQuery(jpql, Paciente.class);
        qry.setParameter("email", email);
        List<Paciente> lst = qry.getResultList();
        this.entityManager.close();
        return !lst.isEmpty() ? lst.get(0) : null;
    }
}
