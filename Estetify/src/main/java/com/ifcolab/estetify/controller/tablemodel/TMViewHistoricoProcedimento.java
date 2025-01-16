package com.ifcolab.estetify.controller.tablemodel;

import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.Procedimento;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TMViewHistoricoProcedimento extends AbstractTableModel {
    private List<Consulta> lista;
    private final int COL_DATA = 0;
    private final int COL_HORA = 1;
    private final int COL_MEDICO = 2;
    private final int COL_PROCEDIMENTOS = 3;
    private final int COL_STATUS = 4;
    
    public TMViewHistoricoProcedimento(List<Consulta> lst) {
        this.lista = lst;
    }
    
    @Override
    public int getRowCount() {
        return lista.size();
    }
    
    @Override
    public int getColumnCount() {
        return 5;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (lista.isEmpty()) {
            return null;
        }
        
        Consulta consulta = lista.get(rowIndex);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        
        switch (columnIndex) {
            case COL_DATA:
                return consulta.getDataHora().format(dateFormatter);
            case COL_HORA:
                return consulta.getDataHora().format(timeFormatter);
            case COL_MEDICO:
                return consulta.getMedico().getNome();
            case COL_PROCEDIMENTOS:
                return consulta.getProcedimentos().stream()
                    .map(Procedimento::getNome)
                    .reduce((a, b) -> a + ", " + b)
                    .orElse("");
            case COL_STATUS:
                return consulta.getStatus().toString();
            default:
                return null;
        }
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case COL_DATA:
                return "Data";
            case COL_HORA:
                return "Hora";
            case COL_MEDICO:
                return "Médico";
            case COL_PROCEDIMENTOS:
                return "Procedimentos";
            case COL_STATUS:
                return "Status";
            default:
                return "";
        }
    }
    
    public Consulta getConsulta(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < lista.size()) {
            return lista.get(rowIndex);
        }
        return null;
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
} 