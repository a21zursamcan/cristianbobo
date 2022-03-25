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
    public int nAccionsAlpha;
    public int nAccionsBeta;
    public int nAccionsDelta;
    public int nAccionsGamma;
    public int nAccionsEpsilon;
    public int nAccionsOmega;
    public jugaBD(int idJugador, int idPartida,
                  int nAccionsAlpha, int nAccionsBeta, int nAccionsDelta, int nAccionsGamma,
                  int nAccionsEpsilon, int nAccionsOmega) {
        this.idJugador = idJugador;
        this.idPartida = idPartida;
        this.nAccionsAlpha = nAccionsAlpha;
        this.nAccionsBeta = nAccionsBeta;
        this.nAccionsDelta = nAccionsDelta;
        this.nAccionsGamma = nAccionsGamma;
        this.nAccionsEpsilon = nAccionsEpsilon;
        this.nAccionsOmega = nAccionsOmega;
    }

    public static ArrayList<jugaBD> obtenirJugades(Connection coneccio) throws SQLException {
        ArrayList <jugaBD> jugadesBD = new ArrayList<>();
         String query = "select id_partida, id_jugador, nAccAlpha, nAccBeta, nAccDelta, nAccGamma, nAccEpsilon, nAccOmega from JUGA";
        try (Statement stmt = coneccio.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            int i=0;
            while (rs.next()) {

               // String jugadorNom = rs.getString("nom");
                int jugadorID = rs.getInt("id_Jugador");
                int partidaID = rs.getInt("id_Partida");
                int nAlpha = rs.getInt("nAccAlpha");
                int nBeta = rs.getInt("nAccBeta");
                int nDelta =rs.getInt("nAccDelta");
                int nGamma =rs.getInt("nAccGamma");
                int nEpsilon =rs.getInt("nAccEpsilon");
                int nOmega = rs.getInt("nAccOmega");
                jugadesBD.add(new jugaBD(jugadorID, partidaID, nAlpha, nBeta, nDelta, nGamma, nEpsilon, nOmega));

               /* System.out.println("ID Jugador: " + jugadesBD.get(i).toString());
                System.out.println("ID Partida: " + jugadesBD.get(i).toString());
                System.out.println("Alpha: " + nAlpha);
                System.out.println("Beta: " + nBeta);
                System.out.println("Delta: " + nDelta);
                System.out.println("Gamma: " + nGamma);
                System.out.println("Epsilon: " + nEpsilon);
                System.out.println("Omega: " + nOmega);*/
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
