package com.ifcolab.estetify.controller.tablemodel;

import com.ifcolab.estetify.model.Enfermeira;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TMViewEnfermeira extends AbstractTableModel {

    private List<Enfermeira> lista;
    private final int COL_NOME = 0;
    private final int COL_COREN = 1;
    private final int COL_EMAIL = 2;
    private final int COL_TELEFONE = 3;

    public TMViewEnfermeira(List<Enfermeira> lst) {
        this.lista = lst;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Enfermeira aux = new Enfermeira();
        if (lista.isEmpty()) {
            return aux;
        } else {
            aux = lista.get(rowIndex);
            switch (columnIndex) {
                case COL_NOME:
                    return aux.getNome();
                case COL_COREN:
                    return aux.getCoren();
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
            case COL_COREN:
                return "COREN";
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
