package com.example.ekonos.persistencia;


import com.example.ekonos.capaDades.jugaBD;
import com.example.ekonos.logica.empresa;
import com.example.ekonos.logica.jugador;
import com.example.ekonos.capaDades.jugadorsBD;
import com.example.ekonos.capaDades.partidaBD;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class persistencia {

    private static Connection conexio = null;
    private static jugador objJugador;

    /** Metodo llamado en taulell(actualitzarDades) para llamar al metodo "inserirJugador"
     * @param jugadors array de jugadores
     * @throws SQLException
     * @throws IOException
     */
    public static void inserirPruebaRealJugador(ArrayList jugadors) throws SQLException, IOException {
        try {
            conexio = coneccioBD.iniciaBD();
            inserirDadesJugadors(jugadors);
        }catch (IOException e){
            System.err.println(e);
        } finally {
            coneccioBD.tancarBD(conexio);
        }
    }

    /** Acceder a la tabla Partida
     * @param empreses array de empresas
     * @throws SQLException
     * @throws IOException
     */
    public static void inserirPruebaRealPartida(ArrayList empreses) throws SQLException, IOException {
        try{
            conexio = coneccioBD.iniciaBD();
            inserirDadesPartida(empreses);

        }catch (IOException e){
            System.err.println(e);
        } finally {
            coneccioBD.tancarBD(conexio);
        }
    }

    /** Acceder a la tabla Juga
     * @param empreses array de empresas
     * @param jugadors array de jugadores
     * @throws SQLException
     * @throws IOException
     */
    public static void inserirPruebaRealJuga(ArrayList empreses, ArrayList jugadors) throws SQLException, IOException {
        try {
            conexio = coneccioBD.iniciaBD();
            inserirDadesJuga(jugadors, empreses, jugadorsBD.obtenirJugadors(conexio), partidaBD.obtenirPartida(conexio));

        }catch (IOException e){
            System.err.println(e);
        } finally {
            coneccioBD.tancarBD(conexio);
        }
    }

    public static void inserirPruebaRealEstadistiques(ArrayList empreses, ArrayList jugadors) throws SQLException, IOException {
        try{
            conexio = coneccioBD.iniciaBD();
            estadisticaJoc(jugadors, empreses, jugaBD.obtenirJugades(conexio));

        }catch (IOException e){
            System.err.println(e);
        } finally {
            coneccioBD.tancarBD(conexio);
        }
    }


    /** Metodo para crear jugadores en base de datos
     * @param jugadors array de jugadores
     * @throws SQLException
     */
    public static void inserirDadesJugadors(ArrayList<jugador> jugadors) throws SQLException {

        boolean jugadorCreat=true;
        final String NOM_TAULA = "JUGADOR";
        String nomJugador = null, nomJugadorEscrit = null;
        int id=0;
        do {
            System.out.println("-BD: Insertant jugador-");
            for (int i=0; i<jugadors.size(); i++){
                nomJugadorEscrit = jugadors.get(i).nom;
                nomJugador = nomJugadorEscrit;
                id = 0;
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
            }
        }while(jugadorCreat==false);
    }

    /** Metodo para crear en la base de datos los aumentos de las filiales en la partida.
     * @param empreses array de empresas
     * @throws SQLException
     */
    public static void inserirDadesPartida(ArrayList<empresa> empreses) throws SQLException {

        //ArrayList<taulell> filials
        //ArrayList<casella> filials

        boolean jugadorCreat=true;
        final String NOM_TAULA = "PARTIDA";

        int filialCrexementAlpha=0, filialCrexementBeta=0, filialCrexementDelta=0, filialCrexementGamma=0, filialCrexementEpsilon=0, filialCrexementOmega=0;

        String nomEmpresa = null;
        //Autoincremente en idPartida
        int idPartida=0;

            System.out.println("-BD: Insertant dades per partida-");

            filialCrexementAlpha = empreses.get(0).nFilials; //sacar filial
            filialCrexementBeta = empreses.get(1).nFilials;
            filialCrexementDelta = empreses.get(2).nFilials;
            filialCrexementGamma = empreses.get(3).nFilials;
            filialCrexementEpsilon = empreses.get(4).nFilials;
            filialCrexementOmega = empreses.get(5).nFilials;
            //nomJugador = nomJugadorEscrit;
            idPartida = 0;
            String sentenciaSQL = " INSERT INTO " + NOM_TAULA + " VALUES (" + idPartida + "," + filialCrexementAlpha + "," +  filialCrexementBeta +  "," + filialCrexementDelta +  ","  + filialCrexementGamma + "," + filialCrexementEpsilon +  "," + filialCrexementOmega + ");";

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
    }

    /** Metodo para crear en la base de datos las acciones por jugador y empresa en la partida.
     * @param jugadors array de jugadores
     * @param empreses array de empresas
     * @param jugadorBS
     * @param dadesPartidaBD
     * @throws SQLException
     */
    public static void inserirDadesJuga(ArrayList<jugador> jugadors, ArrayList<empresa> empreses, ArrayList<jugador> jugadorBS, int dadesPartidaBD) throws SQLException {
        boolean jugadorCreat = true;
        final String NOM_TAULA = "JUGA";
        int id_partida=0, id_jugador=0;
        int nAccionsAlpha, nAccionsBeta, nAccionsDelta, nAccionsGamma, nAccionsEpsilon, nAccionsOmega;
        do {
            System.out.println("-BD: Insertant dades per juga-");
            for (int i = 0; i < jugadors.size(); i++) {
                //Numero de accions del jugador en la empresa

               // id_partida = dadesPartidaBD.get(i).id;
                //!!ID PARTIDA(JUGA) RELACIONADO CON IDPARTIDA(PARTIDA)!!
                id_partida = dadesPartidaBD;
                id_jugador = jugadorBS.get(i).id;
                nAccionsAlpha = empreses.get(0).numeroAccionsJugador(jugadors.get(i));
                nAccionsBeta = empreses.get(1).numeroAccionsJugador(jugadors.get(i));
                nAccionsDelta = empreses.get(2).numeroAccionsJugador(jugadors.get(i));
                nAccionsGamma = empreses.get(3).numeroAccionsJugador(jugadors.get(i));
                nAccionsEpsilon = empreses.get(4).numeroAccionsJugador(jugadors.get(i));
                nAccionsOmega = empreses.get(5).numeroAccionsJugador(jugadors.get(i));

                String sentenciaSQL = " INSERT INTO " + NOM_TAULA + " VALUES (" + id_partida + "," + id_jugador + "," + nAccionsAlpha + "," +nAccionsBeta + "," +nAccionsDelta + "," +nAccionsGamma + "," +nAccionsEpsilon + "," +nAccionsOmega +");"; //Terminar secuencia

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
            }
        } while (jugadorCreat == false);
    }
    public static void estadisticaJoc(ArrayList<jugador> jugadors, ArrayList<empresa> empreses, ArrayList<jugaBD> jugades){
        int filialCrexementAlpha=0, filialCrexementBeta=0, filialCrexementDelta=0, filialCrexementGamma=0, filialCrexementEpsilon=0, filialCrexementOmega=0;
        int numAccAlpha=1, nAccionsBeta=1, nAccionsDelta=1, nAccionsGamma=1, nAccionsEpsilon=1, nAccionsOmega=1;
        String nomJugador;
        int calculEstadisticaAlpha=0, calculEstadisticaBeta=0, calculEstadisticaDelta=0, calculEstadisticaGamma=0, calculEstadisticaEpsilon=0, calculEstadisticaOmega=0;

        filialCrexementAlpha = empreses.get(0).nFilials; //sacar filial
        filialCrexementBeta = empreses.get(1).nFilials;
        filialCrexementDelta = empreses.get(2).nFilials;
        filialCrexementGamma = empreses.get(3).nFilials;
        filialCrexementEpsilon = empreses.get(4).nFilials;
        filialCrexementOmega = empreses.get(5).nFilials;


        for (int i=0; i<jugadors.size(); i++){
            nomJugador = jugadors.get(i).nom.toString();
            numAccAlpha = jugades.get(i).nAccionsAlpha;
            nAccionsBeta = jugades.get(i).nAccionsBeta;
            nAccionsDelta = jugades.get(i).nAccionsDelta;
            nAccionsGamma = jugades.get(i).nAccionsGamma;
            nAccionsEpsilon =  jugades.get(i).nAccionsEpsilon;
            nAccionsOmega = jugades.get(i).nAccionsOmega;

            calculEstadisticaAlpha=numAccAlpha*filialCrexementAlpha;
            calculEstadisticaBeta=nAccionsBeta*filialCrexementBeta;
            calculEstadisticaDelta=nAccionsDelta*filialCrexementDelta;
            calculEstadisticaGamma=nAccionsGamma*filialCrexementGamma;
            calculEstadisticaEpsilon=nAccionsEpsilon*filialCrexementEpsilon;
            calculEstadisticaOmega=nAccionsOmega*filialCrexementOmega;
            System.out.println("\n-Encuesta-");
            System.out.println("El jugador " + nomJugador + ":");
            System.out.println("Puntuacio de Alpha: " + calculEstadisticaAlpha);
            System.out.println("Puntuacio de Beta: " + calculEstadisticaBeta);
            System.out.println("Puntuacio de Delta: " + calculEstadisticaDelta);
            System.out.println("Puntuacio de Gamma: " + calculEstadisticaGamma);
            System.out.println("Puntuacio de Epsilon: " + calculEstadisticaEpsilon);
            System.out.println("Puntuacio de Omega: " + calculEstadisticaOmega);
            System.out.println("Puntuaci?? total: " + calculEstadisticaAlpha + calculEstadisticaBeta + calculEstadisticaDelta + calculEstadisticaGamma + calculEstadisticaEpsilon + calculEstadisticaOmega);
        }

    }
}