package com.ifcolab.estetify.model.dao;

import com.ifcolab.estetify.factory.DatabaseJPA;
import com.ifcolab.estetify.model.Enfermeira;
import com.ifcolab.estetify.model.exceptions.EnfermeiraException;
import java.util.List;

public class EnfermeiraDAO extends Dao<Enfermeira> {
    
    public EnfermeiraDAO() {
    }
    
    @Override
    public boolean delete(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        this.entityManager.getTransaction().begin();
        
        Enfermeira enfermeira = this.entityManager.find(Enfermeira.class, id);
        if (enfermeira == null) {
            this.entityManager.getTransaction().rollback();
            return false;
        }
        
        this.entityManager.remove(enfermeira);
        this.entityManager.getTransaction().commit();
        this.entityManager.close();
        return true;
    }
    
    @Override
    public Enfermeira find(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        Enfermeira e = this.entityManager.find(Enfermeira.class, id);
        this.entityManager.close();
        return e;
    }
    
    @Override
    public List<Enfermeira> findAll() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT e FROM Enfermeira e";
        qry = this.entityManager.createQuery(jpql, Enfermeira.class);
        List<Enfermeira> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }
    
    public List<Enfermeira> filterByName(String nome) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT e FROM Enfermeira e WHERE e.nome LIKE :nome";
        qry = this.entityManager.createQuery(jpql, Enfermeira.class);
        qry.setParameter("nome", nome + "%");
        List<Enfermeira> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }
    
    public Enfermeira findByCPF(String cpf) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT p FROM Enfermeira p WHERE p.cpf = :cpf";
        qry = this.entityManager.createQuery(jpql, Enfermeira.class);
        qry.setParameter("cpf", cpf);
        List<Enfermeira> lst = qry.getResultList();
        this.entityManager.close();
        return !lst.isEmpty() ? lst.get(0) : null;
    }
    
    public Enfermeira findByCOREN(String coren) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT e FROM Enfermeira e WHERE e.coren = :coren";
        qry = this.entityManager.createQuery(jpql, Enfermeira.class);
        qry.setParameter("coren", coren);
        List<Enfermeira> lst = qry.getResultList();
        this.entityManager.close();
        return !lst.isEmpty() ? lst.get(0) : null;
    }
    
    public Enfermeira findByEmail(String email) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT e FROM Enfermeira e WHERE e.email = :email";
        qry = this.entityManager.createQuery(jpql, Enfermeira.class);
        qry.setParameter("email", email);
        List<Enfermeira> lst = qry.getResultList();
        this.entityManager.close();
        return !lst.isEmpty() ? lst.get(0) : null;
    }
}
