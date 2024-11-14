package com.ifcolab.estetify.controller.tablemodel;

import com.ifcolab.estetify.model.Procedimento;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;

public class TMViewProcedimento extends AbstractTableModel {

    private List<Procedimento> lista;
    private final int COL_DESCRICAO = 0;
    private final int COL_DURACAO = 1;
    private final int COL_VALOR = 2;
    private final int COL_REQUISITOS = 3;
    private final int COL_CONTRAINDICACOES = 4;

    public TMViewProcedimento(List<Procedimento> lst) {
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
        Procedimento aux = new Procedimento();
        if (lista.isEmpty()) {
            return aux;
        } else {
            aux = lista.get(rowIndex);
            DecimalFormat decimalFormat = new DecimalFormat("R$ #,##0.00");
            
            switch (columnIndex) {
                case COL_DESCRICAO:
                    return aux.getDescricao();
                case COL_DURACAO:
                    long horas = aux.getDuracaoEstimada().toHours();
                    long minutos = aux.getDuracaoEstimada().toMinutesPart();
                    return String.format("%02d:%02d", horas, minutos);
                case COL_VALOR:
                    return decimalFormat.format(aux.getValor());
                case COL_REQUISITOS:
                    return aux.getRequisitos();
                case COL_CONTRAINDICACOES:
                    return aux.getContraindicacoes();
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
            case COL_DESCRICAO:
                return "Descrição";
            case COL_DURACAO:
                return "Duração";
            case COL_VALOR:
                return "Valor";
            case COL_REQUISITOS:
                return "Requisitos";
            case COL_CONTRAINDICACOES:
                return "Contraindicações";
            default:
                break;
        }
        return "";
    }
}
