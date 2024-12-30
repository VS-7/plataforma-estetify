package com.ifcolab.estetify.controller;

import com.ifcolab.estetify.controller.tablemodel.TMConsultaDia;
import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.Enfermeira;
import com.ifcolab.estetify.model.Medico;
import com.ifcolab.estetify.model.Paciente;
import com.ifcolab.estetify.model.Procedimento;
import com.ifcolab.estetify.model.dao.ConsultaDAO;
import com.ifcolab.estetify.model.exceptions.ConsultaException;
import com.ifcolab.estetify.model.valid.ValidateConsulta;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JTable;

public class ConsultaController {
    
    private ConsultaDAO repositorio;
    private ValidateConsulta validate;
    
    public ConsultaController() {
        repositorio = new ConsultaDAO();
        validate = new ValidateConsulta();
    }
    
    public void cadastrar(
            Paciente paciente,
            Medico medico,
            Enfermeira enfermeira,
            String data,
            String hora,
            List<Procedimento> procedimentos,
            String observacoes) throws ConsultaException {
            
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime dataHora = LocalDateTime.parse(data + " " + hora, formatter);
        
        Consulta consulta = validate.validaCamposEntrada(
            dataHora,
            observacoes,
            paciente,
            medico,
            enfermeira,
            procedimentos
        );
        
        consulta.setStatus("AGENDADA");
        repositorio.save(consulta);
    }
    
    public void atualizar(
            int id,
            Paciente paciente,
            Medico medico,
            Enfermeira enfermeira,
            String data,
            String hora,
            List<Procedimento> procedimentos,
            String observacoes) throws ConsultaException {
            
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime dataHora = LocalDateTime.parse(data + " " + hora, formatter);
        
        Consulta consulta = validate.validaCamposEntrada(
            dataHora,
            observacoes,
            paciente,
            medico,
            enfermeira,
            procedimentos
        );
        
        consulta.setId(id);
        consulta.setStatus("AGENDADA");
        repositorio.update(consulta);
    }
    
    public void excluir(Consulta consulta) throws ConsultaException {
        repositorio.delete(consulta.getId());
    }
    
    public List<Consulta> findAll() {
        return repositorio.findAll();
    }
    
    public void atualizarTabela(JTable grd) {
        List<Consulta> lst = repositorio.findAll();
        TMConsultaDia tableModel = new TMConsultaDia(lst);
        grd.setModel(tableModel);
    }
}
