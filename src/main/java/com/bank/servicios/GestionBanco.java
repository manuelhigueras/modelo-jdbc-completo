
package com.bank.servicios;

import com.bank.dominio.Banco;
import com.bank.excepciones.BankException;
import com.db.PoolConexiones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestionBanco implements GestionBancoInterface {
    
    private static final String SELECT_BANCO_POR_ID = "SELECT ID_BANCO, NOMBRE FROM BANCOS "
                                                      + "WHERE ID_BANCO = ?";

    @Override
    public Banco getBancoPorId(int idBanco) throws BankException, SQLException {
       Banco banco = null;
       Connection con = PoolConexiones.getConexionLibre();
       
       PreparedStatement ps = con.prepareStatement(SELECT_BANCO_POR_ID);
       ps.setInt(1, idBanco);
       ResultSet rs = ps.executeQuery();
       if(rs.next()){
           banco = new Banco(rs.getInt("ID_BANCO"), rs.getString("NOMBRE"));
       }else{
           throw new BankException("No hay un banco con id "+ idBanco);
       }
       
       PoolConexiones.liberaConexion(con);
       return banco;
    }//fin método
    
    
    public static void main(String[] args) {
        try {
            GestionBanco g = new GestionBanco();
            Banco b = g.getBancoPorId(10);
            System.out.println(b);
        } catch (BankException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error de BD:" + ex.getMessage());
        }
    }
    
}
