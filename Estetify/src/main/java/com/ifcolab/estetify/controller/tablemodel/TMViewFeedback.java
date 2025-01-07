package com.ifcolab.estetify.controller.tablemodel;

import com.ifcolab.estetify.model.Feedback;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TMViewFeedback extends AbstractTableModel {
    private List<Feedback> lista;
    private final int COL_TITULO = 0;
    private final int COL_AVALIACAO = 1;
    private final int COL_DATA = 2;
    private final int COL_PACIENTE = 3;
    private final int COL_PROCEDIMENTO = 4;

    public TMViewFeedback(List<Feedback> lst) {
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
        
        Feedback aux = lista.get(rowIndex);
        switch (columnIndex) {
            case COL_TITULO:
                return aux.getTitulo();
            case COL_AVALIACAO:
                return "*".repeat(aux.getAvaliacao());
            case COL_DATA:
                return aux.getDataAvaliacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            case COL_PACIENTE:
                return aux.getConsulta().getPaciente().getNome();
            case COL_PROCEDIMENTO:
                return aux.getConsulta().getProcedimentos().stream()
                        .map(proc -> proc.getNome())
                        .reduce((a, b) -> a + ", " + b)
                        .orElse("");
            default:
                return "";
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case COL_TITULO:
                return "Título";
            case COL_AVALIACAO:
                return "Avaliação";
            case COL_DATA:
                return "Data";
            case COL_PACIENTE:
                return "Paciente";
            case COL_PROCEDIMENTO:
                return "Procedimento";
            default:
                return "";
        }
    }
    
    public Feedback getFeedback(int row) {
        if (row >= 0 && row < lista.size()) {
            return lista.get(row);
        }
        return null;
    }
} 