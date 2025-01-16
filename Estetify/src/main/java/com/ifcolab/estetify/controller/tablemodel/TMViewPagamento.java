package com.ifcolab.estetify.controller.tablemodel;

import com.ifcolab.estetify.model.Pagamento;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TMViewPagamento extends AbstractTableModel {
    private List<Pagamento> lista;
    private final int COL_DATA = 0;
    private final int COL_PACIENTE = 1;
    private final int COL_MEDICO = 2;
    private final int COL_VALOR = 3;
    private final int COL_METODO = 4;
    private final int COL_STATUS = 5;
    
    public TMViewPagamento(List<Pagamento> lst) {
        this.lista = lst;
    }
    
    public int getRowCount() {
        return lista.size();
    }
    
    public int getColumnCount() {
        return 6;
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (lista.isEmpty()) {
            return null;
        }
        
        Pagamento pagamento = lista.get(rowIndex);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        DecimalFormat decimalFormat = new DecimalFormat("R$ #,##0.00");
        
        switch (columnIndex) {
            case COL_DATA:
                return pagamento.getDataPagamento().format(dateFormatter);
            case COL_PACIENTE:
                return pagamento.getConsulta().getPaciente().getNome();
            case COL_MEDICO:
                return pagamento.getConsulta().getMedico().getNome();
            case COL_VALOR:
                return decimalFormat.format(pagamento.getValor());
            case COL_METODO:
                return pagamento.getMetodoPagamento().toString();
            case COL_STATUS:
                return pagamento.getStatus().toString();
            default:
                return null;
        }
    }
    
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case COL_DATA:
                return "Data/Hora";
            case COL_PACIENTE:
                return "Paciente";
            case COL_MEDICO:
                return "Médico";
            case COL_VALOR:
                return "Valor";
            case COL_METODO:
                return "Método";
            case COL_STATUS:
                return "Status";
            default:
                return "";
        }
    }
    
    public Pagamento getPagamento(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < lista.size()) {
            return lista.get(rowIndex);
        }
        return null;
    }
    
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
} 