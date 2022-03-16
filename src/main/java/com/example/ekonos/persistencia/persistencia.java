package com.example.ekonos.persistencia;

import com.example.ekonos.logica.empresa;
import com.example.ekonos.logica.jugador;

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

    /**
     * @return Se ejecuta y devuelve la conexion de la base de datos lista para ser usada en la sentencias SQL
     * @throws SQLException
     * @throws IOException
     */
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

    /** Metodo para cerrar la base de datos
     * @param conexio conexion a la base de datos
     */
    public static void tancarBD(Connection conexio) {
        try {
            System.out.println("Base de dades tancada");
            conexio.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** Metodo llamado en taulell(actualitzarDades) para llamar al metodo "inserirJugador"
     * @param jugadors array de jugadores
     * @throws SQLException
     * @throws IOException
     */
    public static void inserirPruebaReal(ArrayList jugadors) throws SQLException, IOException {
        iniciaBD();
        inserirJugadors(conexio, jugadors);
        tancarBD(conexio);
    }

    /** Metodo para leer y devolver la contraseña del fichero "fitxUsuari"
     * @return devuelve el usuario leido en el fichero
     * @throws IOException
     */
    public static String fitxerUsuari() throws IOException {
        File archivo = new File("fitxUsuari");
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);
        String linea = br.readLine();
        return linea;
    }

    /** Metodo para leer y devolver la contraseña del fichero "fitxPass"
     * @return devuelve la contraseña leida en el fichero
     * @throws IOException
     */
    public static String fitxerPass() throws IOException {
        File archivo = new File("fitxPass");
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);
        String linea = br.readLine();
        return linea;
    }

    /**
     * Metodo para crear jugadores en base de datos
     * @param conexio conexion a la base de datos
     * @param jugadors array de jugadores
     * @throws SQLException
     */
    public static void inserirJugadors(Connection conexio, ArrayList<jugador> jugadors) throws SQLException {

        boolean jugadorCreat=true;
        final String NOM_TAULA = "JUGADOR";
        String nomJugador = null, nomJugadorEscrit = null;
        int id=0, idEscrit = 0;
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

    public static void inserirDadesJuga (Connection conexio, ArrayList<jugador> jugadors, ArrayList<empresa> empresas) throws SQLException {
        boolean jugadorCreat = true;
        final String NOM_TAULA = "JUGA";
        int nAccionsAlpha, nAccionsBeta, nAccionsDelta, nAccionsGamma, nAccionsEpsilon, nAccionsOmega;
        do {
            System.out.println("-BD: Insertant dades per partida-");
            for (int i = 0; i < jugadors.size(); i++) {
                //Numero de accions del jugador en la empresa
                nAccionsAlpha = empresas.get(0).numeroAccionsJugador(jugadors.get(i));
                nAccionsBeta = empresas.get(1).numeroAccionsJugador(jugadors.get(i));
                nAccionsDelta = empresas.get(2).numeroAccionsJugador(jugadors.get(i));
                nAccionsGamma = empresas.get(3).numeroAccionsJugador(jugadors.get(i));
                nAccionsEpsilon = empresas.get(4).numeroAccionsJugador(jugadors.get(i));
                nAccionsOmega = empresas.get(5).numeroAccionsJugador(jugadors.get(i));
                String sentenciaSQL = " INSERT INTO " + NOM_TAULA + " VALUES (" + nAccionsAlpha + ");"; //Terminar secuencia

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


    public static void inserirDadesPartida(Connection conexio, ArrayList<jugador> jugadors, ArrayList<empresa> empresas) throws SQLException {

        //ArrayList<taulell> filials
        //ArrayList<casella> filials

        boolean jugadorCreat=true;
        final String NOM_TAULA = "PARTIDA";

        int filiaCrexementAlpha, filiaCrexementBeta, filiaCrexementDelta, filiaCrexementGamma, filiaCrexementEpsilon, filiaCrexementOmega;
        int nMonedes;

        String nomEmpresa = null, nomJugador;
        //Autoincremente en idPartida
        int idPartida=0;
        do {
            System.out.println("-BD: Insertant dades per partida-");
            for (int i=0; i<jugadors.size(); i++){
               // nomEmpresa = empresas.get(i).nom;
                //Dades del jugador
                nomJugador = jugadors.get(i).nom;
                nMonedes = jugadors.get(i).unitatMonetaria;
                //Numero de factor de empresa
               /* filiaCrexementAlpha = empresas.get(0). //sacar filial*/
                //nomJugador = nomJugadorEscrit;
                idPartida = 0;
                String sentenciaSQL = " INSERT INTO " + NOM_TAULA + " VALUES (" + idPartida + "," + "'" + nomJugador + "'" + ");";

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
}