
package com.bank.servicios;

import com.bank.dominio.Cliente;
import com.bank.excepciones.BankException;
import com.db.PoolConexiones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GestionClientes implements GestionClienteInterface {
    
    private static final String SELECT_ALL_CLIENTES_BANCO = "SELECT * "
            + "FROM  CLIENTES "
            + "WHERE ID_BANCO = ? ";

    @Override
    public List<Cliente> getClientesPorIdBanco(int idBanco) throws BankException, SQLException {
       List<Cliente> clientes = new ArrayList<Cliente>();
       Connection con = PoolConexiones.getConexionLibre();
       
       PreparedStatement ps = con.prepareStatement(SELECT_ALL_CLIENTES_BANCO);
       ps.setInt(1, idBanco);
       ResultSet rs = ps.executeQuery();
       while(rs.next()){
           Cliente c = new Cliente();
           c.setIdCliente(rs.getInt("ID_CLIENTE"));
           c.setNombre(rs.getString("NOMBRE"));
           c.setApellidos(rs.getString("APELLIDOS"));
           c.setFechaNacimiento(rs.getDate("FECHA_NACIMIENTO"));
           c.setIdBanco(rs.getInt("ID_BANCO"));
           clientes.add(c);
       }
       PoolConexiones.liberaConexion(con);
       return clientes;
    }
    
    
    public static void main(String[] args) {
        try {
            GestionClientes gc = new GestionClientes();
            
            List lista = gc.getClientesPorIdBanco(1);
            if(lista.isEmpty()){
                System.out.println("... no tiene clientes");
            }else{
                System.out.println(lista);
            }
            
        } catch (BankException ex) {
            Logger.getLogger(GestionClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GestionClientes.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
        int[] n = {3,4};
        String[] s = {"a"};
        
    }
    
}
