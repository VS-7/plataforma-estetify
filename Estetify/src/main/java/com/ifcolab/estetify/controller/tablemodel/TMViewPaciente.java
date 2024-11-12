package com.ifcolab.estetify.controller.tablemodel;

import com.ifcolab.estetify.model.Paciente;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TMViewPaciente extends AbstractTableModel {

    private List<Paciente> lista;
    private final int COL_NOME = 0;
        private final int COL_SEXO = 1;
    private final int COL_EMAIL = 2;
    private final int COL_CPF = 3;
    private final int COL_TELEFONE = 4;

    public TMViewPaciente(List<Paciente> lst) {
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
        Paciente aux = new Paciente();
        if (lista.isEmpty()) {
            return aux;
        } else {
            aux = lista.get(rowIndex);
            switch (columnIndex) {
                case COL_NOME:
                    return aux.getNome();
                case COL_SEXO:
                    return aux.getSexo();                    
                case COL_EMAIL:
                    return aux.getEmail();
                case COL_CPF:
                    return aux.getCpf();
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
            case COL_SEXO:
                return "Sexo";
            case COL_EMAIL:
                return "Email";
            case COL_CPF:
                return "CPF";
            case COL_TELEFONE:
                return "Telefone";
            default:
                break;
        }
        return "";
    }
} 