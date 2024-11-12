package com.ifcolab.estetify.controller.tablemodel;

import com.ifcolab.estetify.model.Medico;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TMViewMedico extends AbstractTableModel {

    private List<Medico> lista;
    private final int COL_NOME = 0;
    private final int COL_CRM = 1;
    private final int COL_ESPECIALIZACAO = 2;
    private final int COL_EMAIL = 3;
    private final int COL_TELEFONE = 4;

    public TMViewMedico(List<Medico> lst) {
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
        Medico aux = new Medico();
        if (lista.isEmpty()) {
            return aux;
        } else {
            aux = lista.get(rowIndex);
            switch (columnIndex) {
                case COL_NOME:
                    return aux.getNome();
                case COL_CRM:
                    return aux.getCrm();
                case COL_ESPECIALIZACAO:
                    return aux.getEspecializacao();
                case COL_EMAIL:
                    return aux.getEmail();
                case COL_TELEFONE:
                    return aux.getTelefone();
                default:
                    break;
            }
            return aux;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case COL_NOME:
                return "Nome";
            case COL_CRM:
                return "CRM";
            case COL_ESPECIALIZACAO:
                return "Especialização";
            case COL_EMAIL:
                return "Email";
            case COL_TELEFONE:
                return "Telefone";
            default:
                break;
        }
        return "";
    }
}
