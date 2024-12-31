package com.ifcolab.estetify.model.dao;

import com.ifcolab.estetify.factory.DatabaseJPA;
import com.ifcolab.estetify.model.Feedback;
import com.ifcolab.estetify.model.exceptions.FeedbackException;
import java.util.List;
import javax.persistence.TypedQuery;

public class FeedbackDAO extends Dao<Feedback> {
    
    @Override
    public boolean delete(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        this.entityManager.getTransaction().begin();
        Feedback feedback = this.entityManager.find(Feedback.class, id);
        if (feedback != null) {
            this.entityManager.remove(feedback);
        } else {
            this.entityManager.getTransaction().rollback();
            throw new FeedbackException("Erro - Feedback inexistente.");
        }
        this.entityManager.getTransaction().commit();
        this.entityManager.close();
        return true;
    }
    
    @Override
    public Feedback find(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        Feedback feedback = this.entityManager.find(Feedback.class, id);
        this.entityManager.close();
        return feedback;
    }
    
    @Override
    public List<Feedback> findAll() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT DISTINCT f FROM Feedback f LEFT JOIN FETCH f.consulta";
        TypedQuery<Feedback> query = this.entityManager.createQuery(jpql, Feedback.class);
        List<Feedback> lst = query.getResultList();
        this.entityManager.close();
        return lst;
    }
}
