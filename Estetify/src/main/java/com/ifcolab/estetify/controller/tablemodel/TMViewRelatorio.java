package com.ifcolab.estetify.controller.tablemodel;

import com.ifcolab.estetify.model.Relatorio;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TMViewRelatorio extends AbstractTableModel {

    private List<Relatorio> lista;
    private final int COL_DATA = 0;
    private final int COL_CONSULTA = 1;
    private final int COL_RESULTADO = 2;
    private final int COL_OBSERVACOES = 3;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public TMViewRelatorio(List<Relatorio> lst) {
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
        Relatorio aux = new Relatorio();
        if (lista.isEmpty()) {
            return aux;
        } else {
            aux = lista.get(rowIndex);
            switch (columnIndex) {
                case COL_DATA:
                    return aux.getDataEmissao().format(formatter);
                case COL_CONSULTA:
                    return aux.getConsulta().getPaciente().getNome() + " - " + 
                           aux.getConsulta().getDataHora().format(formatter);
                case COL_RESULTADO:
                    return aux.getResultado();
                case COL_OBSERVACOES:
                    return aux.getObservacoes();
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
            case COL_DATA:
                return "Data de Emissão";
            case COL_CONSULTA:
                return "Consulta";
            case COL_RESULTADO:
                return "Resultado";
            case COL_OBSERVACOES:
                return "Observações";
            default:
                break;
        }
        return "";
    }
}