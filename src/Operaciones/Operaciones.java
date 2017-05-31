    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Operaciones;

import Objeto.Persona;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ricky_000
 */
public class Operaciones extends Conexion {
/**
 * Método que añade datos a la base de datos.
 * @param sql
 * @return 
 */
    public boolean insertar(String sql) {
        boolean valor = true;
        conectar();
        try {
            consulta.executeUpdate(sql);
        } catch (SQLException ex) {
            valor = false;
            JOptionPane.showMessageDialog(null, "Error insertar" + ex.getMessage());
        } finally {
            try {
                consulta.close();
                conexion.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return valor;
    }

    /**
     * Método que lee la bd y devuelve el resultado.
     * @param sql
     * @return 
     */
    public ResultSet consultar(String sql) {
        conectar();
        ResultSet resultado = null;
        try {
            resultado = consulta.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println("Mensaje:" + ex.getMessage());
            System.out.println("Estado:" + ex.getSQLState());
            System.out.println("Codigo del error:" + ex.getErrorCode());
            JOptionPane.showMessageDialog(null, "Error consulta    " + ex.getMessage());
        }
        return resultado;
    }

    /**
     * Método para guardar el usuario.
     * @param persona 
     */
    public void guardarUsuario(Persona persona) {
        insertar("insert into Persona values(" + persona.getId()
                + ",'" + persona.getPrimer_nombre()
                + "','" + persona.getSegundo_nombre()
                + "','" + persona.getPrimer_apellido()
                + "','" + persona.getSegundo_apellido() + "')");
    }

    public void totalPersonas(DefaultTableModel tableModel) {
        ResultSet resultado = null;
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        String sql = "select * from Persona";
        try {
            resultado = consultar(sql);
            if (resultado != null) {
                int numeroColumna = resultado.getMetaData().getColumnCount();
                for (int j = 1; j <= numeroColumna; j++) {
                    tableModel.addColumn(resultado.getMetaData().getColumnName(j));
                }
                while (resultado.next()) {
                    Object[] objetos = new Object[numeroColumna];
                    for (int i = 1; i <= numeroColumna; i++) {
                        objetos[i - 1] = resultado.getObject(i);
                    }
                    tableModel.addRow(objetos);
                }
            }
        } catch (SQLException ex) {
        } finally {
            try {
                consulta.close();
                conexion.close();
                if (resultado != null) {
                    resultado.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void eliminar(String idsql) throws SQLException{
        String query ="DELETE FROM REGISTRO WHERE id = "+idsql;
        PreparedStatement rs = conexion.prepareStatement(query);
        rs.execute();
    }
}
