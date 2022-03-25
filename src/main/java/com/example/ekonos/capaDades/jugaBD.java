package com.example.ekonos.capaDades;

import com.example.ekonos.logica.jugador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class jugaBD {
    public int idJugador;
    public int idPartida;
    public jugaBD(int idJugador, int idPartida){
        this.idJugador=idJugador;
        this.idPartida=idPartida;
    }
    public static ArrayList<jugaBD> obtenirJugades(Connection coneccio) throws SQLException {
        ArrayList <jugaBD> jugadesBD = new ArrayList<>();
        String query = "select id_Jugador, id_Partida from JUGA";
        try (Statement stmt = coneccio.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            int i=0;
            while (rs.next()) {

                String jugadorNom = rs.getString("nom");
                int jugadorID = rs.getInt("id_Jugador");
                int partidaID = rs.getInt("id_Partida");
                jugadesBD.add(new jugaBD(jugadorID, partidaID));

                System.out.println("ID Jugador: " + jugadesBD.get(i));
                System.out.println("ID Partida: " + jugadesBD.get(i));
                i++;
            }
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return (jugadesBD);
    }

//    public int getIdJugador() {
//        return idJugador;
//    }
//
//    public void setIdJugador(int idJugador) {
//        this.idJugador = idJugador;
//    }
//
//    public int getIdPartida() {
//        return idPartida;
//    }
//
//    public void setIdPartida(int idPartida) {
//        this.idPartida = idPartida;
//    }
}
