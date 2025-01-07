package com.ifcolab.estetify.model.dao;

import com.ifcolab.estetify.factory.DatabaseJPA;
import com.ifcolab.estetify.model.Admin;
import com.ifcolab.estetify.model.exceptions.AdminException;
import java.util.List;

public class AdminDAO extends Dao<Admin> {
    
    public AdminDAO() {
    }
    
    @Override
    public boolean delete(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        this.entityManager.getTransaction().begin();
        Admin admin = this.entityManager.find(Admin.class, id);
        if (admin != null) {
            this.entityManager.remove(admin);
        } else {
            this.entityManager.getTransaction().rollback();
            throw new AdminException("Erro - Administrador inexistente.");
        }
        this.entityManager.getTransaction().commit();
        this.entityManager.close();
        return true;
    }
    
    @Override
    public Admin find(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        Admin admin = this.entityManager.find(Admin.class, id);
        this.entityManager.close();
        return admin;
    }
    
    @Override
    public List<Admin> findAll() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT a FROM Admin a";
        qry = this.entityManager.createQuery(jpql, Admin.class);
        List<Admin> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }
    
    public List<Admin> filterByName(String nome) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT a FROM Admin a WHERE a.nome LIKE :nome";
        qry = this.entityManager.createQuery(jpql, Admin.class);
        qry.setParameter("nome", nome + "%");
        List<Admin> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }
    
    public Admin findByCPF(String cpf) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT a FROM Admin a WHERE a.cpf = :cpf";
        qry = this.entityManager.createQuery(jpql, Admin.class);
        qry.setParameter("cpf", cpf);
        List<Admin> lst = qry.getResultList();
        this.entityManager.close();
        return !lst.isEmpty() ? lst.get(0) : null;
    }
    
    public Admin findByEmail(String email) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT a FROM Admin a WHERE a.email = :email";
        qry = this.entityManager.createQuery(jpql, Admin.class);
        qry.setParameter("email", email);
        List<Admin> lst = qry.getResultList();
        this.entityManager.close();
        return !lst.isEmpty() ? lst.get(0) : null;
    }
} 