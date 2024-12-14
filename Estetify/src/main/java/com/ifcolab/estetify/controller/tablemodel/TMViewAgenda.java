package com.ifcolab.estetify.controller.tablemodel;

import com.ifcolab.estetify.model.Agenda;
import com.ifcolab.estetify.model.dao.AgendaDAO;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TMViewAgenda extends AbstractTableModel {

    private List<Agenda> lista;
    private final AgendaDAO agendaDAO;
    private final int COL_MEDICO = 0;
    private final int COL_DATA = 1;
    private final int COL_HORARIOS_DISPONIVEIS = 2;
    private final int COL_TOTAL_CONSULTAS = 3;
    private final int COL_ESPECIALIZACAO = 4;

    public TMViewAgenda(List<Agenda> lst) {
        this.lista = lst;
        this.agendaDAO = new AgendaDAO();
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
        
        Agenda aux = lista.get(rowIndex);
        
        switch (columnIndex) {
            case COL_MEDICO:
                return aux.getMedico().getNome();
            case COL_DATA:
                return aux.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            case COL_HORARIOS_DISPONIVEIS:
                return agendaDAO.buscarHorarios(aux).size();
            case COL_TOTAL_CONSULTAS:
                return agendaDAO.buscarConsultas(aux).size();
            case COL_ESPECIALIZACAO:
                return aux.getMedico().getEspecializacao();
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
            case COL_MEDICO:
                return "Médico";
            case COL_DATA:
                return "Data";
            case COL_HORARIOS_DISPONIVEIS:
                return "Horários Disponíveis";
            case COL_TOTAL_CONSULTAS:
                return "Consultas Marcadas";
            case COL_ESPECIALIZACAO:
                return "Especialização";
            default:
                return "";
        }
    }
    
    public Agenda getAgenda(int rowIndex) {
        if (!lista.isEmpty()) {
            return lista.get(rowIndex);
        }
        return null;
    }
    
    public void setLista(List<Agenda> lista) {
        this.lista = lista;
        this.fireTableDataChanged();
    }
}