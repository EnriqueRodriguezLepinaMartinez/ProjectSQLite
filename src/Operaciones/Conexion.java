/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Operaciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author ricky_000
 */
public class Conexion {

    private final String ruta;
    Connection conexion;
    Statement consulta;

    public Conexion() {
        ruta = "C:\\Users\\ricky_000\\Documents\\NetBeansProjects\\Project_SQLite\\registro.db";
    }

    public void conectar() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error Drivers    " + ex.getMessage());
        }
        try {
            conexion = DriverManager.getConnection("jdbc:sqlite:" + ruta);
            consulta = conexion.createStatement();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error conexion    " + ex.getMessage());
        }
    }

}
