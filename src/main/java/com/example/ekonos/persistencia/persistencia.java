package com.example.ekonos.persistencia;

import com.example.ekonos.logica.jugador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class persistencia {

    private static Connection conexio = null;
    private static jugador objJugador;

    public static Connection iniciaBD() throws SQLException, IOException {
        final String IP = "labs.inspedralbes.cat";
        final String NOMBD = "a20crimoldia_ekonos";
        String url = "jdbc:mysql://" + IP + ":3306/" + NOMBD;
        String usuari = fitxerUsuari();
        String password = fitxerPass();
        System.out.println("-Inicia connexió-");
        conexio = DriverManager.getConnection(url, usuari, password);
        System.out.println("Connected to database");
        return conexio;
    }

    public static void tancarBD(Connection conexio) {
        try {
            System.out.println("Base de dades tancada");
            conexio.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException, IOException {
        iniciaBD();
        inserirJugadors(conexio);
        tancarBD(conexio);
    }

    public static String fitxerUsuari() throws IOException {
        File archivo = new File("fitxUsuari");
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);
        String linea = br.readLine();
        return linea;
    }

    public static String fitxerPass() throws IOException {
        File archivo = new File("fitxPass");
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);
        String linea = br.readLine();
        return linea;
    }

    public static void inserirJugadors(Connection conexio) throws SQLException {

        boolean jugadorCreat=true;
        final String NOM_TAULA = "JUGADOR";
        String nomJugador;
        int id;
        do {
            System.out.println("Diga'm el NOM del cafe que vols insertar");
            //nomJugador = objJugador.nom;
            nomJugador = "Pepito";
            //id = objJugador.id;
            id = 1;

            String sentenciaSQL = " INSERT INTO " + NOM_TAULA + " VALUES (" + id + "," + "'" + nomJugador + "'" + ");";

            Statement sta = null;
            try {
                sta = persistencia.conexio.createStatement();
                try {
                    sta.executeUpdate(sentenciaSQL);
                    jugadorCreat = true;
                } catch (SQLException e) {
                    //System.out.println("Error, aquest element que es vol insertar a la base de dades te la mateixa Primary Key");
                    System.err.println(e);
                    jugadorCreat = false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                sta.close();
            }
        }while(jugadorCreat==false);
    }

}
