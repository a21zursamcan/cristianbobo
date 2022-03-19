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


    /** Se ejecuta la conexion de la base de datos lista para ser usada en la sentencias SQL
     * @throws SQLException
     * @throws IOException
     */
    public static void iniciaBD() throws SQLException, IOException {
        final String IP = "labs.inspedralbes.cat";
        final String NOMBD = "a20crimoldia_ekonos";
        String url = "jdbc:mysql://" + IP + ":3306/" + NOMBD;
        String usuari = fitxerUsuari();
        String password = fitxerPass();
        System.out.println("-Inicia connexió-");
        conexio = DriverManager.getConnection(url, usuari, password);
        System.out.println("Connected to database");
        //return conexio;
    }

    /** Metodo para cerrar la base de datos
     */
    public static void tancarBD() {
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
    public static void inserirPruebaRealJugador(ArrayList empreses, ArrayList jugadors) throws SQLException, IOException {
        iniciaBD();
        inserirDadesJugadors(jugadors);
//        inserirDadesPartida(empreses);
//        inserirDadesJuga(empreses, jugadors);
        tancarBD();
    }

    /** Acceder a la tabla Partida
     * @param empreses array de empresas
     * @throws SQLException
     * @throws IOException
     */
    public static void inserirPruebaRealPartida(ArrayList empreses) throws SQLException, IOException {
        iniciaBD();
        inserirDadesPartida(empreses);
        tancarBD();
    }


    /** Acceder a la tabla Juga
     * @param empreses array de empresas
     * @param jugadors array de jugadores
     * @throws SQLException
     * @throws IOException
     */
    public static void inserirPruebaRealJuga(ArrayList empreses, ArrayList jugadors) throws SQLException, IOException {
        iniciaBD();
        inserirDadesJuga(empreses, jugadors);
        tancarBD();
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
                // nomEmpresa = empreses.get(i).nom;
                //Dades del jugador
                //Numero de factor de empresa

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
     * @throws SQLException
     */
    public static void inserirDadesJuga (ArrayList<jugador> jugadors, ArrayList<empresa> empreses) throws SQLException {
        boolean jugadorCreat = true;
        final String NOM_TAULA = "JUGA";
        int id_partida=0, id_jugador=0;
        int nAccionsAlpha, nAccionsBeta, nAccionsDelta, nAccionsGamma, nAccionsEpsilon, nAccionsOmega;
        do {
            System.out.println("-BD: Insertant dades per partida-");
            for (int i = 0; i < jugadors.size(); i++) {
                //Numero de accions del jugador en la empresa

                id_partida = 1;
                id_jugador = 1;
                nAccionsAlpha = 1;
                nAccionsBeta = 1;
                nAccionsDelta = 1;
                nAccionsGamma = 1;
                nAccionsEpsilon = 1;
                nAccionsOmega = 1;

//                nAccionsAlpha = empreses.get(0).numeroAccionsJugador(jugadors.get(i));
//                nAccionsBeta = empreses.get(1).numeroAccionsJugador(jugadors.get(i));
//                nAccionsDelta = empreses.get(2).numeroAccionsJugador(jugadors.get(i));
//                nAccionsGamma = empreses.get(3).numeroAccionsJugador(jugadors.get(i));
//                nAccionsEpsilon = empreses.get(4).numeroAccionsJugador(jugadors.get(i));
//                nAccionsOmega = empreses.get(5).numeroAccionsJugador(jugadors.get(i));


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



}