package com.ifcolab.estetify.model.dao;

import com.ifcolab.estetify.factory.DatabaseJPA;
import com.ifcolab.estetify.model.Recepcionista;
import java.util.List;

public class RecepcionistaDAO extends Dao<Recepcionista> {
    
    public RecepcionistaDAO() {
    }
    
    @Override
    public boolean delete(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        this.entityManager.getTransaction().begin();
        
        Recepcionista recepcionista = this.entityManager.find(Recepcionista.class, id);
        if (recepcionista == null) {
            this.entityManager.getTransaction().rollback();
            return false;
        }
        
        this.entityManager.remove(recepcionista);
        this.entityManager.getTransaction().commit();
        this.entityManager.close();
        return true;
    }
    
    @Override
    public Recepcionista find(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        Recepcionista r = this.entityManager.find(Recepcionista.class, id);
        this.entityManager.close();
        return r;
    }
    
    @Override
    public List<Recepcionista> findAll() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT r FROM Recepcionista r";
        qry = this.entityManager.createQuery(jpql, Recepcionista.class);
        List<Recepcionista> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }
    
    public List<Recepcionista> filterByName(String nome) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT r FROM Recepcionista r WHERE r.nome LIKE :nome";
        qry = this.entityManager.createQuery(jpql, Recepcionista.class);
        qry.setParameter("nome", nome + "%");
        List<Recepcionista> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }
    
    public Recepcionista findByEmail(String email) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT r FROM Recepcionista r WHERE r.email = :email";
        qry = this.entityManager.createQuery(jpql, Recepcionista.class);
        qry.setParameter("email", email);
        List<Recepcionista> lst = qry.getResultList();
        this.entityManager.close();
        return !lst.isEmpty() ? lst.get(0) : null;
    }
    
    public Recepcionista findByCPF(String cpf) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT r FROM Recepcionista r WHERE r.cpf = :cpf";
        qry = this.entityManager.createQuery(jpql, Recepcionista.class);
        qry.setParameter("cpf", cpf);
        List<Recepcionista> lst = qry.getResultList();
        this.entityManager.close();
        return !lst.isEmpty() ? lst.get(0) : null;
    }
}
