package com.example.ekonos.persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class accesBD {
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
}
