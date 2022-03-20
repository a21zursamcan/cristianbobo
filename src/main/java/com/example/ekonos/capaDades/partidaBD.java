package com.example.ekonos.capaDades;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class partidaBD {

//    public int id;
//
//    public partidaBD (int id){
//        this.id = id;
//    }

    public static int obtenirPartida(Connection coneccio) throws SQLException {
        int id = 0;
        ArrayList <partidaBD> dadesPartidaBD = new ArrayList<>();
        String query = "select id_partida from PARTIDA";
        try (Statement stmt = coneccio.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            int i=0;
            while (rs.next()) {
                id = rs.getInt("id_partida");
//                dadesPartidaBD.add(new partidaBD(id));

                System.out.println("\nID Partida: " + id);
                i++;
            }
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return (id);
    }
}
