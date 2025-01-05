package com.ifcolab.estetify.model.dao;

import com.ifcolab.estetify.factory.DatabaseJPA;
import com.ifcolab.estetify.model.Pagamento;
import com.ifcolab.estetify.model.exceptions.PagamentoException;
import java.util.List;
import javax.persistence.TypedQuery;

public class PagamentoDAO extends Dao<Pagamento> {
    
    @Override
    public boolean delete(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        this.entityManager.getTransaction().begin();
        Pagamento pagamento = this.entityManager.find(Pagamento.class, id);
        if (pagamento != null) {
            this.entityManager.remove(pagamento);
        } else {
            this.entityManager.getTransaction().rollback();
            throw new PagamentoException("Erro - Pagamento inexistente.");
        }
        this.entityManager.getTransaction().commit();
        this.entityManager.close();
        return true;
    }
    
    @Override
    public Pagamento find(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        Pagamento pagamento = this.entityManager.find(Pagamento.class, id);
        this.entityManager.close();
        return pagamento;
    }
    
    @Override
    public List<Pagamento> findAll() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT p FROM Pagamento p";
        TypedQuery<Pagamento> query = this.entityManager.createQuery(jpql, Pagamento.class);
        List<Pagamento> pagamentos = query.getResultList();
        this.entityManager.close();
        return pagamentos;
    }
    
    public List<Pagamento> buscarPorConsulta(int consultaId) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        try {
            jpql = "SELECT p FROM Pagamento p WHERE p.consulta.id = :consultaId";
            TypedQuery<Pagamento> query = this.entityManager.createQuery(jpql, Pagamento.class);
            query.setParameter("consultaId", consultaId);
            return query.getResultList();
        } finally {
            this.entityManager.close();
        }
    }
}
