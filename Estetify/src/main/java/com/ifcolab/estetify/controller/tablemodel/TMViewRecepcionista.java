package com.ifcolab.estetify.controller.tablemodel;

import com.ifcolab.estetify.model.Recepcionista;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TMViewRecepcionista extends AbstractTableModel {

    private List<Recepcionista> lista;
    private final int COL_NOME = 0;
    private final int COL_CPF = 1;
    private final int COL_EMAIL = 2;
    private final int COL_TELEFONE = 3;
    private final int COL_DATA_CONTRATACAO = 4;

    public TMViewRecepcionista(List<Recepcionista> lst) {
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
        Recepcionista aux = new Recepcionista();
        if (lista.isEmpty()) {
            return aux;
        } else {
            aux = lista.get(rowIndex);
            switch (columnIndex) {
                case COL_NOME:
                    return aux.getNome();
                case COL_CPF:
                    return aux.getCpf();
                case COL_EMAIL:
                    return aux.getEmail();
                case COL_TELEFONE:
                    return aux.getTelefone();
                case COL_DATA_CONTRATACAO:
                    return aux.getDataContratacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
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
            case COL_CPF:
                return "CPF";
            case COL_EMAIL:
                return "Email";
            case COL_TELEFONE:
                return "Telefone";
            case COL_DATA_CONTRATACAO:
                return "Data Contratação";
            default:
                break;
        }
        return "";
    }
}
