package com.example.ekonos.logica;

import com.example.ekonos.HelloController;
import com.example.ekonos.persistencia.persistencia;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.sql.SQLException;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class taulell {

    // Variables
    ArrayList<empresa> empreses = new ArrayList<empresa>();
    ArrayList<carta> baralla = new ArrayList<carta>();
    public ArrayList<jugador> jugadors = new ArrayList<jugador>();
    ArrayList<casella> caselles = new ArrayList<casella>();
    HelloController hc;
    public jugador jugadorActual;
    public Image imatgeActualEmpresa = null;
    public boolean jocIniciat = false;
    public boolean cartaColocada = false;
    public int numeroRondas;
    public int cartesPerTorn;
    public jugador primerJugador = null;
    public int numeroRonda = 0;
    public int numeroJugadorActual = 0;
    public int numeroTorn = 0;
    public int numeroTorns = 0;

    public void demo(HelloController hc) throws SQLException, IOException {
        this.hc=hc;
        generarEmpreses();
        jugadors=new ArrayList<jugador>();
        jugadors.add(new jugador(null,1, "PericoM"));
        jugadors.add(new jugador(null,2, "PedroM"));
        jugadors.add(new jugador(null,3, "PacoM"));
        jugadors.add(new jugador(null,4, "PabloM"));
        jugadors.add(new jugador(null,5, "PatricioM"));
        jugadors.add(new jugador(null,6, "SamuelM"));
        insertarJugadoresBaseDatos();
        jugadorActual = jugadors.get(0);
//        hc.demoButton.setVisible(false);
        empreses.get(0).nFilials = 3;
        empreses.get(1).nFilials = 4;
        empreses.get(2).nFilials = 2;
        empreses.get(3).nFilials = 4;
        empreses.get(4).nFilials = 1;
        empreses.get(5).nFilials = 2;
        //persistencia.inserirDadesPartida(empreses);
        insertarEmpresesBaseDatos();
        empreses.get(0).accions.add(jugadors.get(0));
        empreses.get(0).accions.add(jugadors.get(5));
        empreses.get(0).accions.add(jugadors.get(3));
        empreses.get(0).accions.add(jugadors.get(4));
        empreses.get(0).accions.add(jugadors.get(2));
        empreses.get(0).president=jugadors.get(0);
        empreses.get(1).accions.add(jugadors.get(5));
        empreses.get(1).accions.add(jugadors.get(1));
        empreses.get(1).president=jugadors.get(5);
        empreses.get(2).accions.add(jugadors.get(3));
        empreses.get(2).accions.add(jugadors.get(5));
        empreses.get(2).accions.add(jugadors.get(2));
        empreses.get(2).accions.add(jugadors.get(1));
        empreses.get(2).accions.add(jugadors.get(1));
        empreses.get(2).accions.add(jugadors.get(1));
        empreses.get(2).president=jugadors.get(1);
        empreses.get(3).accions.add(jugadors.get(4));
        empreses.get(3).accions.add(jugadors.get(0));
        empreses.get(3).president=jugadors.get(4);
        empreses.get(4).accions.add(jugadors.get(0));
        empreses.get(4).president=jugadors.get(0);
        empreses.get(5).accions.add(jugadors.get(4));
        empreses.get(5).accions.add(jugadors.get(4));
        empreses.get(5).accions.add(jugadors.get(2));
        empreses.get(5).president=jugadors.get(4);
        insertarJugaBaseDatos();
        crearEstadistica();
        empreses.get(0).FactorCreixement = 3;
        empreses.get(1).FactorCreixement = 2;
        empreses.get(2).FactorCreixement = 4;
        empreses.get(3).FactorCreixement = 1;
        empreses.get(4).FactorCreixement = 5;
        empreses.get(5).FactorCreixement = 3;
        actualitzarDades();
        //persistencia.inserirDadesJuga(jugadors, empreses);
    }
    public void insertarJugadoresBaseDatos() throws SQLException, IOException {
        persistencia.inserirPruebaRealJugador(jugadors);
    }
    public void insertarEmpresesBaseDatos() throws SQLException, IOException {
        persistencia.inserirPruebaRealPartida(empreses);
    }
    public void insertarJugaBaseDatos() throws SQLException, IOException {
        persistencia.inserirPruebaRealJuga(empreses, jugadors);
    }

   public void crearEstadistica() throws SQLException, IOException {
      persistencia.inserirPruebaRealEstadistiques(empreses, jugadors);
   }

    public void ReproducirSonido(String nombreSonido) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(nombreSonido));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.out.println(ex);
        }
    }

    public Image obteImatgeEmpresaActual() {

        File f = new File(imatgeActualEmpresa.getUrl());
        String nomImatge = f.getName();
        nomImatge = nomImatge.substring(0, (nomImatge.length() - 4));
        nomImatge = nomImatge + "F.png";
        return new Image(f.getParent() + File.separator + nomImatge);
    }

    public void insertaJugador(Image peçaJugador, HelloController hc, String nom) throws SQLException, IOException {
        System.out.println(nom);
        this.hc = hc;
        int numeroJugador = jugadors.size();
        jugadors.add(new jugador(peçaJugador, numeroJugador, nom));
        if (jugadors.size() == 3) {
            hc.setTitol1("SELECCIONA LA PEÇA DEL JUGADOR 4");
            hc.mostrarNext();
        } else if (jugadors.size() == 6) {
            hc.onNextClick();
        } else {
            hc.setTitol1("SELECCIONA LA PEÇA DEL JUGADOR " + (jugadors.size() + 1));
        }
    }

    public void actualitzarDades() throws SQLException, IOException {
        hc.cambiaMonedas(jugadorActual.unitatMonetaria);
        hc.actualitzaCartes();
        actualitzaFilialsEmpreses();
        for (int i = 0; i < empreses.size(); i++) {
            empreses.get(i).actualitzaImatgePresident();
            empreses.get(i).actualitzaImatgesAccions();
            empreses.get(i).actualitzaFactorCreixement();
            actualitzaAccionsPantallaJugador(empreses.get(i));
        }
        comprobaFilialsEmpreses();
    }

    public void comprobaFilialsEmpreses() {
        for (empresa empresa : empreses) {
            boolean teFilial = false;
            for (int x = 0; x < caselles.size(); x++) {
                try {
                    if (tradueixEmpresaImg(caselles.get(x).imgView) == empresa) {
                        teFilial = true;
                        empresa.teFilial = true;
                        x = caselles.size();
                    }
                } catch (NullPointerException e) {

                }
            }
            if (!teFilial) {
                empresa.teFilial = false;
            }
        }
    }


    public void actualitzaAccionsPantallaJugador(empresa empresa) {
        int accions;
        int accionstotals;
        accions = empreses.get(0).numeroAccionsJugador(jugadorActual);
        hc.alphaJugadorAccions.setText(accions + "");
        accionstotals = accions;
        accions = empreses.get(1).numeroAccionsJugador(jugadorActual);
        accionstotals = accionstotals + accions;
        hc.betaJugadorAccions.setText(accions + "");
        accions = empreses.get(2).numeroAccionsJugador(jugadorActual);
        accionstotals = accionstotals + accions;
        hc.gammaJugadorAccions.setText(accions + "");
        accions = empreses.get(3).numeroAccionsJugador(jugadorActual);
        accionstotals = accionstotals + accions;
        hc.deltaJugadorAccions.setText(accions + "");
        accions = empreses.get(4).numeroAccionsJugador(jugadorActual);
        accionstotals = accionstotals + accions;
        hc.epsilonJugadorAccions.setText(accions + "");
        accions = empreses.get(5).numeroAccionsJugador(jugadorActual);
        accionstotals = accionstotals + accions;
        hc.ultimaJugadorAccions.setText(accions + "");

        hc.totalJugadorAccions.setText(accionstotals + "");
    }

    public void compraAccions(String nomEmpresa) throws SQLException, IOException {
        empresa empresaActual = tradueixEmpresa(nomEmpresa);
        if (cartaColocada && empresaActual.accions.size() <= 8 && jugadorActual.unitatMonetaria >= empresaActual.valorCompra() && empresaActual.teFilial) {
            this.ReproducirSonido(hc.sonidoCompra);

            //jugadorActual=jugadors.get(0);
            jugador jugador = this.jugadorActual;
            jugador.unitatMonetaria = jugador.unitatMonetaria - empresaActual.valorCompra();
            empresaActual.accions.add(jugador);
            jugador.accions--;

            if (empresaActual.president == null) {
                //Fem al jugador actual president
                empresaActual.president = jugador;
                System.out.println("Felicitats! Ets el nou president d'aquesta empresa, ja que ets la primera persona en adquirir una acció");
            } else {
                //Si hi ha un president comprovem si el jugador té més accions que el president
                if (empresaActual.numeroAccionsJugador(jugador) > empresaActual.numeroAccionsJugador(empresaActual.president)) {
                    empresaActual.president = jugador;
                    System.out.println("Felicitats! Ets el nou president d'aquesta empresa, ja que t'acabas de convertir en el mayor accionista!");
                }
            }
            this.actualitzarDades();
        }
    }

    public void vendreAccions(String nomEmpresa) throws SQLException, IOException {
        empresa empresaActual = tradueixEmpresa(nomEmpresa);
        if (cartaColocada && empresaActual.numeroAccionsJugador(jugadorActual) > 0 && empresaActual.teFilial) {
            this.ReproducirSonido(hc.sonidoVenta);
            //jugadorActual=jugadors.get(0);
            jugador jugador = this.jugadorActual;
            jugador.unitatMonetaria = jugador.unitatMonetaria + empresaActual.valorVenta();
            empresaActual.eliminaUltimaAccio(jugador);
            jugador.accions++;

            //Comprobar el nou president

            if (empresaActual.president == jugador) {
                //Llista amb els posibles substituts per president
                ArrayList<jugador> posiblesPresidents = new ArrayList<jugador>();
                for (int i = 0; i < jugadors.size(); i++) {
                    if (jugadors.get(i) == jugador) {
                        //Si es el mateix jugador no es comproba res
                    } else {
                        if (empresaActual.numeroAccionsJugador(jugadors.get(i)) > empresaActual.numeroAccionsJugador(empresaActual.president)) {
                            posiblesPresidents.add(jugadors.get(i));
                        }
                    }
                }
                if (posiblesPresidents.size() > 1) {
                    hc.seleccionaUnNouPresident(posiblesPresidents, empresaActual);
                    System.out.println("Felicitats! " + empresaActual.president.nom + ", s'ha convertit en el nou president");
                } else if (posiblesPresidents.size() == 1) {
                    empresaActual.president = posiblesPresidents.get(0);
                    System.out.println(empresaActual.president.nom + " s'ha convertit en el nou president per que es el mayor accionista!");
                } else if (posiblesPresidents.size() == 0 && empresaActual.numeroAccionsJugador(jugador) == 0) {
                    empresaActual.president = null;
//                    empresaActual.actualitzaImatgePresident();
                }
            }
            this.actualitzarDades();
        }
    }

    public void nouTorn() throws SQLException, IOException {

        cartaColocada=false;

        if (numeroTorn < numeroTorns) {
            if(numeroJugadorActual<jugadors.size()){
                numeroJugadorActual++;
                tornJugador(numeroJugadorActual-1);

            }else{
                numeroTorn++;
                numeroJugadorActual=0;
                nouTorn();

            }
        } else {
            iniciRonda();
            numeroTorn=0;
        }
    }

    public int getAccionsJugadorActual() {
        return jugadorActual.unitatMonetaria;
    }

    public empresa tradueixEmpresa(String nomEmpresa) {
        for (int i = 0; i < empreses.size(); i++) {
            if (empreses.get(i).nom.equalsIgnoreCase(nomEmpresa)) {
                return empreses.get(i);
            }
        }
        return null;
    }

    public void iniciarJoc() throws SQLException, IOException {
        this.generarEmpreses();
        this.generaCaselles();
        this.numeroRondas = this.seleccionaNumeroRondes();

        this.cartesPerTorn = this.seleccionaCartesPerTorn();
        this.numeroTorns = this.cartesPerTorn;
        this.iniciRonda();
        this.inicialitzaImatgesAccions();
        jocIniciat = true;
    }

    public void inicialitzaImatgesAccions() {
        hc.accionsAlphaImatges.add(hc.alphaAccio1);
        hc.accionsAlphaImatges.add(hc.alphaAccio2);
        hc.accionsAlphaImatges.add(hc.alphaAccio3);
        hc.accionsAlphaImatges.add(hc.alphaAccio4);
        hc.accionsAlphaImatges.add(hc.alphaAccio5);
        hc.accionsAlphaImatges.add(hc.alphaAccio6);
        hc.accionsAlphaImatges.add(hc.alphaAccio7);
        hc.accionsAlphaImatges.add(hc.alphaAccio8);

        hc.accionsBetaImatges.add(hc.betaAccio1);
        hc.accionsBetaImatges.add(hc.betaAccio2);
        hc.accionsBetaImatges.add(hc.betaAccio3);
        hc.accionsBetaImatges.add(hc.betaAccio4);
        hc.accionsBetaImatges.add(hc.betaAccio5);
        hc.accionsBetaImatges.add(hc.betaAccio6);
        hc.accionsBetaImatges.add(hc.betaAccio7);
        hc.accionsBetaImatges.add(hc.betaAccio8);

        hc.accionsDeltaImatges.add(hc.deltaAccio1);
        hc.accionsDeltaImatges.add(hc.deltaAccio2);
        hc.accionsDeltaImatges.add(hc.deltaAccio3);
        hc.accionsDeltaImatges.add(hc.deltaAccio4);
        hc.accionsDeltaImatges.add(hc.deltaAccio5);
        hc.accionsDeltaImatges.add(hc.deltaAccio6);
        hc.accionsDeltaImatges.add(hc.deltaAccio7);
        hc.accionsDeltaImatges.add(hc.deltaAccio8);

        hc.accionsEpsilonImatges.add(hc.epsilonAccio1);
        hc.accionsEpsilonImatges.add(hc.epsilonAccio2);
        hc.accionsEpsilonImatges.add(hc.epsilonAccio3);
        hc.accionsEpsilonImatges.add(hc.epsilonAccio4);
        hc.accionsEpsilonImatges.add(hc.epsilonAccio5);
        hc.accionsEpsilonImatges.add(hc.epsilonAccio6);
        hc.accionsEpsilonImatges.add(hc.epsilonAccio7);
        hc.accionsEpsilonImatges.add(hc.epsilonAccio8);

        hc.accionsGammaImatges.add(hc.gammaAccio1);
        hc.accionsGammaImatges.add(hc.gammaAccio2);
        hc.accionsGammaImatges.add(hc.gammaAccio3);
        hc.accionsGammaImatges.add(hc.gammaAccio4);
        hc.accionsGammaImatges.add(hc.gammaAccio5);
        hc.accionsGammaImatges.add(hc.gammaAccio6);
        hc.accionsGammaImatges.add(hc.gammaAccio7);
        hc.accionsGammaImatges.add(hc.gammaAccio8);

        hc.accionsOmegaImatges.add(hc.omegaAccio1);
        hc.accionsOmegaImatges.add(hc.omegaAccio2);
        hc.accionsOmegaImatges.add(hc.omegaAccio3);
        hc.accionsOmegaImatges.add(hc.omegaAccio4);
        hc.accionsOmegaImatges.add(hc.omegaAccio5);
        hc.accionsOmegaImatges.add(hc.omegaAccio6);
        hc.accionsOmegaImatges.add(hc.omegaAccio7);
        hc.accionsOmegaImatges.add(hc.omegaAccio8);

    }

    public void iniciRonda() throws SQLException, IOException {
        numeroRonda++;
        if (numeroRonda <= numeroRondas) {
            //Generem les cartes
            this.generarCartes();
            //Remenem les cartes
            Collections.shuffle(this.baralla);
            nouTorn();
        }
    }

    public void tornJugador(int jugador) throws SQLException, IOException {
        jugadorActual = jugadors.get(jugador);
        cobrament(jugadors.get(jugador));
        jugadorActual.ma = agafaCartes(cartesPerTorn);
        hc.setTitol1("TORN DEL JUGADOR NUMERO "+(jugador+1));
        hc.imgJugadorActual.setImage(jugadorActual.peça);
        actualitzarDades();
    }

    public void cobrament(jugador jugador) {
        //Crea un nou valor
        int total = jugador.unitatMonetaria;
        if (jugadors.size() == 6 || jugadors.size() == 4 || jugadors.size() == 3) {
            //Suma el numero de unitats monetaries al total
            total = total + 12;
        } else {
            if (jugadors.size() == 5) {
                total = total + 10;
            }
        }
        jugador.unitatMonetaria = total;
    }

    public void generaCaselles() {
        // Casella(numeroCasella, conexio1, conexio2, conexio3, ...)
        this.novaCasella(hc.casella1, 1, 2, 8);
        this.novaCasella(hc.casella2, 2, 1, 3, 8);
        this.novaCasella(hc.casella3, 3, 2, 4, 10);
        this.novaCasella(hc.casella4, 4, 3, 5, 10);
        this.novaCasella(hc.casella5, 5, 4, 6, 11);
        this.novaCasella(hc.casella6, 6, 5, 7, 11);
        this.novaCasella(hc.casella7, 7, 6, 11, 17);
        this.novaCasella(hc.casella8, 8, 1, 2, 12);
        this.novaCasella(hc.casella9, 9, 10, 12, 13, 14);
        this.novaCasella(hc.casella10, 10, 3, 4, 9, 15);
        this.novaCasella(hc.casella11, 11, 5, 6, 7, 16);
        this.novaCasella(hc.casella12, 12, 8, 9, 18);
        this.novaCasella(hc.casella13, 13, 9, 14, 18, 19);
        this.novaCasella(hc.casella14, 14, 9, 13, 15, 20);
        this.novaCasella(hc.casella15, 15, 10, 14, 16, 21);
        this.novaCasella(hc.casella16, 16, 11, 15, 17, 21);
        this.novaCasella(hc.casella17, 17, 7, 16, 24);
        this.novaCasella(hc.casella18, 18, 12, 13, 26, 27);
        this.novaCasella(hc.casella19, 19, 13, 20, 27, 28);
        this.novaCasella(hc.casella20, 20, 14, 19, 21, 22);
        this.novaCasella(hc.casella21, 21, 15, 16, 20, 22);
        this.novaCasella(hc.casella22, 20, 21, 23, 29);
        this.novaCasella(hc.casella23, 23, 22, 24, 30);
        this.novaCasella(hc.casella24, 24, 17, 23, 30, 31);
        this.novaCasella(hc.casella25, 25, 26, 32, 33);
        this.novaCasella(hc.casella26, 26, 18, 25, 27, 34);
        this.novaCasella(hc.casella27, 27, 18, 19, 26, 28);
        this.novaCasella(hc.casella28, 28, 19, 27, 29, 34, 35);
        this.novaCasella(hc.casella29, 29, 22, 28, 35);
        this.novaCasella(hc.casella30, 30, 23, 24, 35, 36);
        this.novaCasella(hc.casella31, 31, 24, 36);
        this.novaCasella(hc.casella32, 32, 25, 33);
        this.novaCasella(hc.casella33, 33, 25, 32, 34);
        this.novaCasella(hc.casella34, 34, 26, 28, 33);
        this.novaCasella(hc.casella35, 35, 28, 29, 30, 36);
        this.novaCasella(hc.casella36, 36, 30, 31, 35);
        // Una vegada que s'ha creat cada casella afegim cada casella com objecte
        this.tradueirConexions();
    }

    public void novaCasella(ImageView imgView, int... c) {
        if (c.length == 3) {
            caselles.add(new casella(imgView, c[0], c[1], c[2]));
        }
        if (c.length == 4) {
            caselles.add(new casella(imgView, c[0], c[1], c[2], c[3]));
        }
        if (c.length == 5) {
            caselles.add(new casella(imgView, c[0], c[1], c[2], c[3], c[4]));
        }
        if (c.length == 6) {
            caselles.add(new casella(imgView, c[0], c[1], c[2], c[3], c[4], c[5]));
        }

    }

    public void tradueirConexions() {
        for (int x = 0; x < caselles.size(); x++) {
            for (int y = 0; y < caselles.get(x).conexionsNumeriques.size(); y++) {
                caselles.get(x).conexions.add(caselles.get(caselles.get(x).conexionsNumeriques.get(y) - 1));
            }
        }
    }

    public jugador seleccionarPrimerJugador(int ronda, jugador anteriorPrimerJugador) {
        if (ronda == 1) {
            // Si es la primera ronda seleccionem un jugador aleatori
            int numeroAleatori = (int) Math.round(Math.random() * (jugadors.size() - 1));

            return jugadors.get((int) numeroAleatori);
        } else {
            // Si no es el primer torn
            int nouJugador = anteriorPrimerJugador.id;
            // Comprobem que no ha arribat al ultim jugador
            if (nouJugador == jugadors.size()) {
                // Si hem arribat a l'ultim jugador li tocara al primer jugador
                return jugadors.get(0);
            } else {
                // Si no hem arribat a l'ultim jugador li tocara al seg�ent
                return jugadors.get(nouJugador);
            }
        }
    }

    public int seleccionaNumeroRondes() {
        if (jugadors.size() == 6) {
            return 2;
        }
        if (jugadors.size() == 5 || jugadors.size() == 4) {
            return 3;
        }
        if (jugadors.size() == 3) {
            return 4;
        }
        return 0;
    }

    public int seleccionaCartesPerTorn() {
        if (jugadors.size() == 6 || jugadors.size() == 4 || jugadors.size() == 3) {
            return 6;
        }
        if (jugadors.size() == 5) {
            return 5;

        }
        return 0;
    }

    public ArrayList<carta> agafaCartes(int numeroCartes) {
        ArrayList<carta> ma = new ArrayList<carta>();
        for (int x = 0; x < numeroCartes; x++) {
            // Afegim l'ultima carta a la ma del jugador y la eliminem de la baralla
            ma.add(baralla.get(baralla.size() - 1));
            baralla.remove(baralla.size() - 1);
        }
        return ma;
    }

    public void generarCartes() {
        // Alpha
        this.novaCarta("Alpha", "beta", "epsilon");
        this.novaCarta("Alpha", "beta", "gamma");
        this.novaCarta("Alpha", "beta", "delta");
        this.novaCarta("Alpha", "delta", "epsilon");
        this.novaCarta("Alpha", "gamma", "epsilon");
        this.novaCarta("Alpha", "delta", "gamma");
        // Delta
        this.novaCarta("Delta", "gamma", "omega");
        this.novaCarta("Delta", "gamma", "alpha");
        this.novaCarta("Delta", "epsilon", "omega");
        this.novaCarta("Delta", "omega", "alpha");
        this.novaCarta("Delta", "epsilon", "alpha");
        this.novaCarta("Delta", "epsilon", "gamma");
        // Beta
        this.novaCarta("Beta", "epsilon", "omega");
        this.novaCarta("Beta", "delta", "gamma");
        this.novaCarta("Beta", "epsilon", "gamma");
        this.novaCarta("Beta", "gamma", "omega");
        this.novaCarta("Beta", "delta", "omega");
        this.novaCarta("Beta", "delta", "epsilon");
        // Gamma
        this.novaCarta("Gamma", "beta", "delta");
        this.novaCarta("Gamma", "omega", "delta");
        this.novaCarta("Gamma", "alpha", "beta");
        this.novaCarta("Gamma", "omega", "alpha");
        this.novaCarta("Gamma", "alpha", "delta");
        this.novaCarta("Gamma", "beta", "omega");
        // Omega
        this.novaCarta("Omega", "beta", "delta");
        this.novaCarta("Omega", "delta", "epsilon");
        this.novaCarta("Omega", "alpha", "delta");
        this.novaCarta("Omega", "alpha", "epsilon");
        this.novaCarta("Omega", "alpha", "beta");
        this.novaCarta("Omega", "beta", "epsilon");
        // Epsilon
        this.novaCarta("Epsilon", "gamma", "omega");
        this.novaCarta("Epsilon", "gamma", "alpha");
        this.novaCarta("Epsilon", "omega", "alpha");
        this.novaCarta("Epsilon", "omega", "beta");
        this.novaCarta("Epsilon", "alpha", "beta");
        this.novaCarta("Epsilon", "gamma", "beta");
    }

    public void novaCarta(String empresa, String color1, String color2) {
        int numEmpresa = -1;
        // Busquem una empresa coincident
        for (int x = 0; x < empreses.size(); x++) {
            if (empresa.equals(empreses.get(x).nom)) {
                numEmpresa = x;
            }
        }
        // Creem la carta
        baralla.add(new carta(empreses.get(numEmpresa), color1, color2));
    }

    public ArrayList<Pane> generarPanesEmpreses(int numeroEmpresa) {
        ArrayList<Pane> factorCreixement = new ArrayList<>();
        switch (numeroEmpresa) {
            case 1:
                factorCreixement.add(hc.alphaFC1);
                factorCreixement.add(hc.alphaFC2);
                factorCreixement.add(hc.alphaFC3);
                factorCreixement.add(hc.alphaFC4);
                factorCreixement.add(hc.alphaFC5);
                factorCreixement.add(hc.alphaFC6);
                factorCreixement.add(hc.alphaFC7);
                break;
            case 2:
                factorCreixement.add(hc.betaFC1);
                factorCreixement.add(hc.betaFC2);
                factorCreixement.add(hc.betaFC3);
                factorCreixement.add(hc.betaFC4);
                factorCreixement.add(hc.betaFC5);
                factorCreixement.add(hc.betaFC6);
                factorCreixement.add(hc.betaFC7);
                break;
            case 3:
                factorCreixement.add(hc.gammaFC1);
                factorCreixement.add(hc.gammaFC2);
                factorCreixement.add(hc.gammaFC3);
                factorCreixement.add(hc.gammaFC4);
                factorCreixement.add(hc.gammaFC5);
                factorCreixement.add(hc.gammaFC6);
                factorCreixement.add(hc.gammaFC7);
                break;
            case 4:
                factorCreixement.add(hc.deltaFC1);
                factorCreixement.add(hc.deltaFC2);
                factorCreixement.add(hc.deltaFC3);
                factorCreixement.add(hc.deltaFC4);
                factorCreixement.add(hc.deltaFC5);
                factorCreixement.add(hc.deltaFC6);
                factorCreixement.add(hc.deltaFC7);
                break;
            case 5:
                factorCreixement.add(hc.epsilonFC1);
                factorCreixement.add(hc.epsilonFC2);
                factorCreixement.add(hc.epsilonFC3);
                factorCreixement.add(hc.epsilonFC4);
                factorCreixement.add(hc.epsilonFC5);
                factorCreixement.add(hc.epsilonFC6);
                factorCreixement.add(hc.epsilonFC7);

                break;
            case 6:
                factorCreixement.add(hc.omegaFC1);
                factorCreixement.add(hc.omegaFC2);
                factorCreixement.add(hc.omegaFC3);
                factorCreixement.add(hc.omegaFC4);
                factorCreixement.add(hc.omegaFC5);
                factorCreixement.add(hc.omegaFC6);
                factorCreixement.add(hc.omegaFC7);
                break;
        }
        return factorCreixement;
    }

    public void generarEmpreses() {
        empreses.add(new empresa("Alpha", "vermell", hc.presidentAlpha, hc.accionsAlphaImatges, generarPanesEmpreses(1)));
        empreses.add(new empresa("Beta", "blauFort", hc.presidentBeta, hc.accionsBetaImatges, generarPanesEmpreses(2)));
        empreses.add(new empresa("Gamma", "blau", hc.presidentGamma, hc.accionsGammaImatges, generarPanesEmpreses(3)));
        empreses.add(new empresa("Delta", "verd", hc.presidentDelta, hc.accionsDeltaImatges, generarPanesEmpreses(4)));
        empreses.add(new empresa("Epsilon", "groc", hc.presidentEpsilon, hc.accionsEpsilonImatges, generarPanesEmpreses(5)));
        empreses.add(new empresa("Omega", "rosa", hc.presidentOmega, hc.accionsOmegaImatges, generarPanesEmpreses(6)));

    }

    public Image traduirColor(String color) {
        return new Image("file:src" + File.separator + "main" + File.separator + "resources" + File.separator + "com" + File.separator + "example" + File.separator + "ekonos" + File.separator + "images" + File.separator + color + "C.png");
    }

    public void actualitzaFilialsEmpreses() {
        for (int x = 0; x < empreses.size(); x++) {
            int contador = 0;
            for (casella casella : caselles) {
                if (casella.empresa == empreses.get(x)) {
                    contador++;
                }
            }
            empreses.get(x).nFilials = contador;
//            System.out.println(empreses.get(x).nom+empreses.get(x).nFilials);
        }
    }

    public void colocaCasella(ImageView imgCasella) throws SQLException, IOException {

        if (this.jocIniciat && this.cartaColocada == false) {
            if (this.imatgeActualEmpresa != null) {
                imgCasella.setImage(this.obteImatgeEmpresaActual());
                casella casella = buscaCasella(imgCasella);
                casella.imgView = imgCasella;
                casella.setEmpresa(tradueixEmpresaImg(imgCasella));
                this.imatgeActualEmpresa = null;
                this.cartaColocada = true;
            }

        }
        actualitzarDades();
    }

    public void clickAument(ImageView imgView) throws SQLException, IOException {
        Image img = imgView.getImage();
        empresa empresa = tradueixEmpresaColor(img);
        empresa.FactorCreixement++;
        cartaColocada = true;
        actualitzarDades();
    }

    public empresa tradueixEmpresaColor(Image img) {
        String ruta = img.getUrl();
        String[] divisiones = ruta.split("/");
        ruta = divisiones[divisiones.length - 1];
        ruta = ruta.substring(0, ruta.length() - 5);
        for (int x = 0; x < empreses.size(); x++) {
            if (ruta.equalsIgnoreCase(empreses.get(x).nom)) {
                return empreses.get(x);
            }
        }
        empresa empresa = null;
        return empresa;
    }

    public empresa tradueixEmpresaImg(ImageView img) {
        File f = new File(img.getImage().getUrl());
        String nom = f.getName();
        nom = nom.substring(0, nom.length() - 5);
        return tradueixEmpresa(nom);
    }

    public casella buscaCasella(ImageView imgCasella) {
        for (int x = 0; x < caselles.size(); x++) {
            if (caselles.get(x).imgView == imgCasella) {
                return caselles.get(x);
            }
        }
        return null;
    }
}
