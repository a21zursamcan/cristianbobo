package com.example.ekonos.persistencia;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class coneccioBD {
    /** Se ejecuta la conexion de la base de datos lista para ser usada en la sentencias SQL
     * @throws SQLException
     * @throws IOException
     */
    public static Connection iniciaBD() throws SQLException, IOException {
        final String IP = "labs.inspedralbes.cat";
        final String NOMBD = "a20crimoldia_ekonos";
        String url = "jdbc:mysql://" + IP + ":3306/" + NOMBD;
        String usuari = accesBD.fitxerUsuari();
        String password = accesBD.fitxerPass();
        System.out.println("-Inicia connexió-");
        System.out.println("Connected to database");
        return DriverManager.getConnection(url, usuari, password);
    }

    /** Metodo para cerrar la base de datos
     */
    public static void tancarBD(Connection conexio) {
        try {
            System.out.println("Base de dades tancada");
            conexio.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
