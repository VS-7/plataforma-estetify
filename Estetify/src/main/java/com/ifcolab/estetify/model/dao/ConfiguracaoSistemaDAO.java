package com.ifcolab.estetify.model.dao;

import com.ifcolab.estetify.factory.DatabaseJPA;
import com.ifcolab.estetify.model.ConfiguracaoSistema;
import java.util.List;

public class ConfiguracaoSistemaDAO extends Dao<ConfiguracaoSistema> {
    
    public ConfiguracaoSistemaDAO() {
    }
    
    @Override
    public boolean delete(int id) {
        return false; // Não permitimos excluir a configuração do sistema
    }
    
    @Override
    public ConfiguracaoSistema find(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        ConfiguracaoSistema config = this.entityManager.find(ConfiguracaoSistema.class, id);
        this.entityManager.close();
        return config;
    }
    
    @Override
    public List<ConfiguracaoSistema> findAll() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT c FROM ConfiguracaoSistema c";
        qry = this.entityManager.createQuery(jpql, ConfiguracaoSistema.class);
        List<ConfiguracaoSistema> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }
    
    public ConfiguracaoSistema getConfiguracao() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        try {
            ConfiguracaoSistema config = this.entityManager.find(ConfiguracaoSistema.class, 1);
            
            if (config == null) {
                config = criarConfiguracaoPadrao();
            }
            
            return config;
        } finally {
            this.entityManager.close();
        }
    }
    
    private ConfiguracaoSistema criarConfiguracaoPadrao() {
        ConfiguracaoSistema config = new ConfiguracaoSistema();
        config.setId(1);
        config.setHorarioAbertura(java.time.LocalTime.of(8, 0));
        config.setHorarioFechamento(java.time.LocalTime.of(18, 0));
        config.setIntervaloConsultaMinutos(30);
        config.setFuncionaSegunda(true);
        config.setFuncionaTerca(true);
        config.setFuncionaQuarta(true);
        config.setFuncionaQuinta(true);
        config.setFuncionaSexta(true);
        config.setFuncionaSabado(false);
        config.setFuncionaDomingo(false);
        config.setTempoMinimoAntecedenciaMinutos(60);
        config.setTempoMaximoAgendamentoDias(60);
        
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.persist(config);
            this.entityManager.getTransaction().commit();
            return config;
        } catch (Exception e) {
            if (this.entityManager.getTransaction().isActive()) {
                this.entityManager.getTransaction().rollback();
            }
            return null;
        } finally {
            this.entityManager.close();
        }
    }
} 