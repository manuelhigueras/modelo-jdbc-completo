package com.bank.servicios;

import com.bank.dominio.Cliente;
import com.bank.dominio.CuentaBancaria;
import com.bank.excepciones.BankException;
import com.db.PoolConexiones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestionCuentasBancarias implements GestionCuentasBancariasInterface {

    private static final String SELECT_GET_CUENTAS_BANCARIAS_CLIENTE
            = "SELECT ID_CUENTA, IBAN, SALDO, ID_CLIENTE "
            + "FROM CUENTAS_BANCARIAS "
            + "WHERE ID_CLIENTE = ?";
    private static final String INSERT_CUENTA_BANCARIA = "INSERT INTO CUENTAS_BANCARIAS"
            + "(ID_CUENTA, IBAN, SALDO, ID_CLIENTE) "
            + "VALUES (?,?,?,?)";
    private static final String MAX_ID_CUENTA_BANCARIA = "SELECT MAX(ID_CUENTA) "
            + "FROM CUENTAS_BANCARIAS";
    
    private static final String UPDATE_SALDO_CUENTA_BANCARIA_POR_IBAN = "UPDATE CUENTAS_BANCARIAS "
            + "SET SALDO = SALDO + ? "
            + "WHERE IBAN = ?";

    @Override
    public List<CuentaBancaria> getCuentasBancariasPorCliente(int idCliente) throws BankException, SQLException {
        List<CuentaBancaria> cuentas = new ArrayList<CuentaBancaria>();
        Connection con = PoolConexiones.getConexionLibre();

        PreparedStatement ps = con.prepareStatement(SELECT_GET_CUENTAS_BANCARIAS_CLIENTE);
        ps.setInt(1, idCliente);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            CuentaBancaria c = new CuentaBancaria();
            c.setIdCliente(rs.getInt("ID_CLIENTE"));
            c.setIban(rs.getString("IBAN"));
            c.setSaldo(rs.getDouble("SALDO"));
            c.setIdCuenta(rs.getInt("ID_CUENTA"));
            cuentas.add(c);
        }
        PoolConexiones.liberaConexion(con);
        return cuentas;
    }

    @Override
    public void altaNuevaCuenta(int idCliente, CuentaBancaria cuenta) throws BankException, SQLException {
   
        Connection con = PoolConexiones.getConexionLibre();

        //obtener el último id de cuenta bancaria 
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(MAX_ID_CUENTA_BANCARIA);
        int nuevoId = 1;
        if(rs.next()){
            nuevoId  = rs.getInt(1) +1;
        }
        System.out.printf("Nuevo Id %d" , nuevoId);
         
        PreparedStatement ps = con.prepareStatement(INSERT_CUENTA_BANCARIA);
        ps.setInt(1, nuevoId);
        ps.setString(2, cuenta.getIban());
        ps.setDouble(3, cuenta.getSaldo());
        ps.setInt(4, idCliente);
        ps.executeUpdate();
        
        PoolConexiones.liberaConexion(con);
    }

    @Override
    public void ingresar(int isbn, double importe) throws BankException, SQLException {
   
        //1. validar importe > 0
        if(importe <= 0 ){
            throw new BankException("No se hizo el ingreso. Debe indicar un importe mayor a 0. ");
        }
        
        Connection con = PoolConexiones.getConexionLibre();

        //obtener el último id de cuenta bancaria 
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(MAX_ID_CUENTA_BANCARIA);
        int nuevoIdCuentaBancaria = 1;
        if(rs.next()){
            nuevoIdCuentaBancaria  = rs.getInt(1) + 1;
        }
        System.out.printf("Nuevo Id %d" , nuevoIdCuentaBancaria);
         
        PreparedStatement ps = con.prepareStatement(INSERT_CUENTA_BANCARIA);
        CuentaBancaria cuenta = new CuentaBancaria();
        ps.setInt(1, nuevoIdCuentaBancaria);
        ps.setString(2, cuenta.getIban());
        ps.setDouble(3, cuenta.getSaldo());
        ps.setInt(4, cuenta.getIdCliente());
        ps.executeUpdate();
        
        PoolConexiones.liberaConexion(con);
    
    }

    @Override
    public void sacar(int isbn, double importe) throws BankException, SQLException {
    }

    
    public static void main(String[] args) {
        try {
            GestionCuentasBancarias gcb = new GestionCuentasBancarias();
            gcb.altaNuevaCuenta(2, new CuentaBancaria(0,"IN7585555",3000.0,2));
            
        } catch (BankException ex) {
            Logger.getLogger(GestionCuentasBancarias.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GestionCuentasBancarias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
}
