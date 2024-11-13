package com.ifcolab.estetify.controller.tablemodel;

import com.ifcolab.estetify.model.Procedimento;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TMViewProcedimento extends AbstractTableModel {

    private List<Procedimento> lista;
    private final int COL_DATA_HORA = 0;
    private final int COL_DESCRICAO = 1;
    private final int COL_PACIENTE = 2;
    private final int COL_MEDICO = 3;
    private final int COL_ENFERMEIRA = 4;
    private final int COL_DURACAO = 5;
    private final int COL_VALOR = 6;

    public TMViewProcedimento(List<Procedimento> lst) {
        this.lista = lst;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Procedimento aux = new Procedimento();
        if (lista.isEmpty()) {
            return aux;
        } else {
            aux = lista.get(rowIndex);
            switch (columnIndex) {
                case COL_DATA_HORA:
                    return aux.getDataHoraFormatada();
                case COL_DESCRICAO:
                    return aux.getDescricao();
                case COL_PACIENTE:
                    return aux.getPaciente().getNome();
                case COL_MEDICO:
                    return aux.getMedico().getNome();
                case COL_ENFERMEIRA:
                    return aux.getEnfermeira().getNome();
                case COL_DURACAO:
                    return aux.getDuracaoFormatada();
                case COL_VALOR:
                    return aux.getValorFormatado();
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
            case COL_DATA_HORA:
                return "Data/Hora";
            case COL_DESCRICAO:
                return "Descrição";
            case COL_PACIENTE:
                return "Paciente";
            case COL_MEDICO:
                return "Médico";
            case COL_ENFERMEIRA:
                return "Enfermeira";
            case COL_DURACAO:
                return "Duração";
            case COL_VALOR:
                return "Valor";
            default:
                break;
        }
        return "";
    }
}
