package com.ifcolab.estetify.controller.tablemodel;

import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.Procedimento;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;
import java.util.stream.Collectors;

public class TMViewConsulta extends AbstractTableModel {

    private List<Consulta> lista;
    private final int COL_DATA_HORA = 0;
    private final int COL_PACIENTE = 1;
    private final int COL_MEDICO = 2;
    private final int COL_ENFERMEIRA = 3;
    private final int COL_PROCEDIMENTOS = 4;
    private final int COL_VALOR_TOTAL = 5;
    private final int COL_STATUS = 6;
    private final int COL_OBSERVACOES = 7;
    private final int COL_ACAO = 8;

    public TMViewConsulta(List<Consulta> lst) {
        this.lista = lst;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 9;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Consulta aux = new Consulta();
        if (lista.isEmpty()) {
            return aux;
        } else {
            aux = lista.get(rowIndex);
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            DecimalFormat decimalFormat = new DecimalFormat("R$ #,##0.00");
            
            switch (columnIndex) {
                case COL_DATA_HORA:
                    return aux.getDataHora().format(dateFormatter);
                    
                case COL_PACIENTE:
                    return aux.getPaciente().getNome();
                    
                case COL_MEDICO:
                    return aux.getMedico().getNome();
                    
                case COL_ENFERMEIRA:
                    return aux.getEnfermeira().getNome();
                    
                case COL_PROCEDIMENTOS:
                    return aux.getProcedimentos().stream()
                            .map(Procedimento::getDescricao)
                            .collect(Collectors.joining(", "));
                    
                case COL_VALOR_TOTAL:
                    return decimalFormat.format(aux.getValorTotal());
                    
                case COL_STATUS:
                    return aux.getStatus();
                    
                case COL_OBSERVACOES:
                    return aux.getObservacoes();
                    
                case COL_ACAO:
                    return "[*]";
                    
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
                
            case COL_PACIENTE:
                return "Paciente";
                
            case COL_MEDICO:
                return "Médico";
                
            case COL_ENFERMEIRA:
                return "Enfermeira";
                
            case COL_PROCEDIMENTOS:
                return "Procedimentos";
                
            case COL_VALOR_TOTAL:
                return "Valor Total";
                
            case COL_STATUS:
                return "Status";
                
            case COL_OBSERVACOES:
                return "Observações";
                
            case COL_ACAO:
                return "Ação";
                
            default:
                break;
        }
        return "";
    }
    
    public Consulta getConsulta(int row) {
        if (row >= 0 && row < lista.size()) {
            return lista.get(row);
        }
        return null;
    }
}