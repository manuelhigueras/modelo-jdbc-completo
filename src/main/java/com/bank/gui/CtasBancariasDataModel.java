
package com.bank.gui;

import com.bank.dominio.CuentaBancaria;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class CtasBancariasDataModel extends AbstractTableModel {

    private List<CuentaBancaria> cuentas;
    private String[] columnas = {"ID_CUENTA","IBAN","SALDO","ID_CLIENTE"};

    public CtasBancariasDataModel(List<CuentaBancaria> cuentas) {
        this.cuentas = cuentas;
    }

    @Override
    public int getRowCount() {
        return cuentas.size() ;
    }

    @Override
    public int getColumnCount() {
        return columnas.length ;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         CuentaBancaria c = cuentas.get(rowIndex);
         Object dato = null;
         switch(columnIndex){
            case 0:
                dato = c.getIdCuenta();
                break;
            case 1:
                dato = c.getIban();
                break;
            case 2:
                dato = c.getSaldo();
                break;
            case 3:
                dato = c.getIdCliente();
         }      
         return dato;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column]; 
    }
    
}
