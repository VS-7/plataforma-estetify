package com.ifcolab.estetify.controller;

import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.Enfermeira;
import com.ifcolab.estetify.model.Medico;
import com.ifcolab.estetify.model.Paciente;
import com.ifcolab.estetify.model.Procedimento;
import com.ifcolab.estetify.model.dao.ConsultaDAO;
import com.ifcolab.estetify.model.valid.ValidateConsulta;
import java.time.LocalDateTime;
import java.util.List;

public class ConsultaController {
    
    private ConsultaDAO repositorio;
    private ValidateConsulta validador;
    
    public ConsultaController() {
        repositorio = new ConsultaDAO();
        validador = new ValidateConsulta();
    }
    
    public void cadastrar(
            LocalDateTime dataHora,
            String observacoes,
            Paciente paciente,
            Medico medico,
            Enfermeira enfermeira,
            List<Procedimento> procedimentos
    ) {
        Consulta consulta = validador.validaCamposEntrada(
            dataHora,
            observacoes,
            paciente,
            medico,
            enfermeira,
            procedimentos
        );
        
        repositorio.save(consulta);
    }
    
    public void excluir(Consulta consulta) {
        if (consulta != null) {
            repositorio.delete(consulta.getId());
        }
    }
    
    public List<Consulta> findAll() {
        return repositorio.findAll();
    }
}
