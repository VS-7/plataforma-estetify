package com.ifcolab.estetify.controller;

import com.ifcolab.estetify.controller.tablemodel.TMViewConsulta;
import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.Enfermeira;
import com.ifcolab.estetify.model.Medico;
import com.ifcolab.estetify.model.Paciente;
import com.ifcolab.estetify.model.Procedimento;
import com.ifcolab.estetify.model.dao.ConsultaDAO;
import com.ifcolab.estetify.model.exceptions.ConsultaException;
import com.ifcolab.estetify.model.valid.ValidateConsulta;
import com.ifcolab.estetify.model.enums.StatusConsulta;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JTable;

public class ConsultaController {
    
    private ConsultaDAO dao;
    private ValidateConsulta validate;
    
    public ConsultaController() {
        dao = new ConsultaDAO();
        validate = new ValidateConsulta();
    }
    
    public void cadastrar(
            LocalDateTime dataHora,
            String observacoes,
            Paciente paciente,
            Medico medico,
            Enfermeira enfermeira,
            List<Procedimento> procedimentos) throws ConsultaException {
            
        Consulta consulta = validate.validaCamposEntrada(
            dataHora,
            observacoes,
            paciente,
            medico,
            enfermeira,
            procedimentos
        );
        
        dao.save(consulta);
    }
    
    public void atualizar(
            int id,
            LocalDateTime dataHora,
            String observacoes,
            Paciente paciente,
            Medico medico,
            Enfermeira enfermeira,
            List<Procedimento> procedimentos,
            StatusConsulta status) throws ConsultaException {
            
        Consulta consulta = validate.validaCamposEntrada(
            dataHora,
            observacoes,
            paciente,
            medico,
            enfermeira,
            procedimentos
        );
        
        consulta.setId(id);
        consulta.setStatus(status);
        dao.update(consulta);
    }
    
    public void excluir(Consulta consulta) throws ConsultaException {
        dao.delete(consulta.getId());
    }
    
    public List<Consulta> findAll() {
        return dao.findAll();
    }
    
    public void atualizarTabela(JTable grd) {
        List<Consulta> lst = dao.findAll();
        TMViewConsulta tableModel = new TMViewConsulta(lst);
        grd.setModel(tableModel);
    }
}
