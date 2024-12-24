package com.ifcolab.estetify.controller.tablemodel;

import com.ifcolab.estetify.model.Consulta;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TMConsultaDia extends AbstractTableModel {
    
    private List<Consulta> consultas;
    private final int COL_HORARIO = 0;
    private final int COL_PACIENTE = 1;
    private final int COL_MEDICO = 2;
    private final int COL_ENFERMEIRA = 3;
    private final int COL_PROCEDIMENTOS = 4;
    private final int COL_STATUS = 5;
    private final int COL_ACAO = 6;
    
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    
    public TMConsultaDia(List<Consulta> consultas) {
        this.consultas = consultas;
    }
    
    @Override
    public int getRowCount() {
        return consultas.size();
    }
    
    @Override
    public int getColumnCount() {
        return 7;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (consultas.isEmpty()) {
            return null;
        }
        
        Consulta consulta = consultas.get(rowIndex);
        
        return switch (columnIndex) {
            case COL_HORARIO -> consulta.getDataHora().format(timeFormatter);
            case COL_PACIENTE -> consulta.getPaciente().getNome();
            case COL_MEDICO -> consulta.getMedico().getNome();
            case COL_ENFERMEIRA -> consulta.getEnfermeira().getNome();
            case COL_PROCEDIMENTOS -> consulta.getProcedimentos().stream()
                    .map(proc -> proc.getDescricao())
                    .reduce((a, b) -> a + ", " + b)
                    .orElse("");
            case COL_STATUS -> consulta.getStatus();
            case COL_ACAO -> "[*]";
            default -> "";
        };
    }
    
    @Override
    public String getColumnName(int column) {
        return switch (column) {
            case COL_HORARIO -> "Horário";
            case COL_PACIENTE -> "Paciente";
            case COL_MEDICO -> "Médico";
            case COL_ENFERMEIRA -> "Enfermeira";
            case COL_PROCEDIMENTOS -> "Procedimentos";
            case COL_STATUS -> "Status";
            case COL_ACAO -> "Ação";
            default -> "";
        };
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
    public Consulta getConsulta(int row) {
        if (row >= 0 && row < consultas.size()) {
            return consultas.get(row);
        }
        return null;
    }
    
    public void atualizarConsultas(List<Consulta> novasConsultas) {
        this.consultas = novasConsultas;
        fireTableDataChanged();
    }
}