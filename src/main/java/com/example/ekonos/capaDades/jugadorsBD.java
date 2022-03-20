package com.example.ekonos.capaDades;

import com.example.ekonos.logica.jugador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class jugadorsBD {
    public static ArrayList<jugador> obtenirJugadors(Connection coneccio) throws SQLException {
        ArrayList <jugador> jugadorsBD = new ArrayList<jugador>();
        String query = "select id, nom from JUGADOR";
        try (Statement stmt = coneccio.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            int i=0;
            while (rs.next()) {

                String jugadorNom = rs.getString("nom");
                int jugadorID = rs.getInt("id");
                jugadorsBD.add(new jugador(null, jugadorID, jugadorNom));

                System.out.println("\nJugador: " + jugadorNom + ", " + jugadorID);
                System.out.println("Nom Jugador: " + jugadorsBD.get(i).nom);
                System.out.println("ID Jugador: " + jugadorsBD.get(i).id);
                i++;
            }
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return (jugadorsBD);
    }
}
